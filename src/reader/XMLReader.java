package reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import factory.PresentationFactory;
import factory.SlideFactory;
import factory.SlideItemFactory;
import model.Presentation;
import model.Slide;
import model.SlideItem;

/**
 * Verantwoordelijk voor het inlezen van {@link Presentation}s uit een
 * XML-bestand.
 * 
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 */
public class XMLReader implements Reader {

	/** Default API to use. */
	protected static final String DEFAULT_API_TO_USE = "dom";

	/** namen van xml tags of attributen */
	protected static final String SHOWTITLE = "showtitle";
	protected static final String SLIDETITLE = "title";
	protected static final String SLIDES = "slides";
	protected static final String SLIDE = "slide";
	protected static final String SLIDESEQUENCES = "slidesequences";
	protected static final String SLIDESEQUENCE = "slidesequence";
	protected static final String SLIDENUMBER = "slidenumber";
	protected static final String SEQUENCETITLE = "title";
	protected static final String ITEM = "item";
	protected static final String LEVEL = "level";
	protected static final String KIND = "kind";
	protected static final String TEXT = "text";
	protected static final String IMAGE = "image";

	/** tekst van messages */
	protected static final String PCE = "Parser Configuration Exception";
	protected static final String UNKNOWNTYPE = "Unknown Element type";
	protected static final String NFE = "Number Format Exception";

	private String filename;

	/**
	 * Constructor. Slaat de meegegeven bestandsnaam op.
	 * 
	 * @param filename
	 *            Naam van het xml-bestand.
	 */
	public XMLReader(String filename) {
		this.filename = filename;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Presentation> load() {
		return loadPresentations(this.filename);
	}

	/**
	 * Geeft de inhoud terug van het eerste child-element met een bepaalde tagname.
	 * 
	 * @param element
	 *            Parent element waarvan de child-elements wordt opgevraagd.
	 * @param tagName
	 *            tagname Naam van het xml-element dat gezocht wordt.
	 * @return Inhoud van het eerste child-element met een gegeven tagname.
	 */
	private String getTitle(Element element, String tagName) {
		NodeList titles = element.getElementsByTagName(tagName);
		return titles.item(0).getTextContent();
	}

	/**
	 * Leest alle {@link Presentation}s uit een xml-bestand.
	 * 
	 * @param filename
	 *            Naam van het bestand dat ingelezen wordt.
	 * @return Een lijst van ingelezen {@link Presentation}s.
	 */
	protected List<Presentation> loadPresentations(String filename) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(new File(filename)); // maak een JDOM document
			Element doc = document.getDocumentElement(); // root node (<presentation>)
			String presentationTitle = getTitle(doc, SHOWTITLE);

			// process slides
			Node slidesContainer = doc.getElementsByTagName(SLIDES).item(0);
			List<Slide> slides = processSlides(slidesContainer);

			// process slide sequences
			Node sequenceContainer = doc.getElementsByTagName(SLIDESEQUENCES).item(0);
			List<Presentation> presentations = processSlideSequences(sequenceContainer, presentationTitle, slides);

			return presentations;

		} catch (IOException iox) {
			System.err.println(iox.toString());
		} catch (SAXException sax) {
			System.err.println(sax.getMessage());
		} catch (ParserConfigurationException pcx) {
			System.err.println(PCE);
		}

		// something went wrong
		return null;
	}

	/**
	 * Verwerkt de xml-node dat informatie bevat over {@link Slide}s.
	 * 
	 * @param slidesContainer
	 *            De xml root node dat alle slide nodes bevat.
	 * @return Een lijst van ingelezen {@link Slide}s.
	 */
	private List<Slide> processSlides(Node slidesContainer) {
		List<Slide> slides = new ArrayList<Slide>();

		NodeList slideNodes = slidesContainer.getChildNodes();
		int slidesCount = slideNodes.getLength();
		for (int slideNumber = 0; slideNumber < slidesCount; slideNumber++) {
			Node slideNode = slideNodes.item(slideNumber);
			if (slideNode.getNodeType() != Node.ELEMENT_NODE)
				continue;

			// get title for slide
			Element slideElement = (Element) slideNode;
			String title = getTitle(slideElement, SLIDETITLE);
			Slide slide = SlideFactory.getInstance().getSlide(title);

			// process all slide items
			NodeList slideItemNodes = slideElement.getElementsByTagName(ITEM);
			int itemCount = slideItemNodes.getLength();
			for (int itemNumber = 0; itemNumber < itemCount; itemNumber++) {
				Element item = (Element) slideItemNodes.item(itemNumber);
				SlideItem slideItem = createSlideItem(item);
				slide.append(slideItem);
			}

			slides.add(slide);
		}
		return slides;
	}

	/**
	 * Maakt een {@link SlideItem} aan op basis van een xml-element.
	 * 
	 * @param item
	 *            Het xml-element met informatie over een slide item.
	 * @return Een nieuwe {@link SlideItem}.
	 */
	protected SlideItem createSlideItem(Element item) {
		int level = 1; // default
		NamedNodeMap attributes = item.getAttributes();
		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			} catch (NumberFormatException x) {
				System.err.println(NFE);
			}
		}

		String type = attributes.getNamedItem(KIND).getTextContent();
		SlideItem slideItem = SlideItemFactory.getInstance().getSlideItem(level, type, item.getTextContent());

		return slideItem;
	}

	/**
	 * Verwert alle slide-sequences nodes.
	 * 
	 * @param sequenceContainer
	 *            De xml root node dat alle slide-sequence nodes bevat.
	 * @param title
	 *            Hoofdtitel voor de aan te maken {@link Presentation}s.
	 * @param slides
	 *            {@link Slide}s die gerefereerd kunnen worden vanuit een
	 *            {@link Presentation}.
	 * @return Een lijst met aangemaakte {@link Presentation}s.
	 */
	private List<Presentation> processSlideSequences(Node sequenceContainer, String title, List<Slide> slides) {
		List<Presentation> presentations = new ArrayList<Presentation>();

		NodeList sequenceNodes = sequenceContainer.getChildNodes();
		int sequenceCount = sequenceNodes.getLength();
		for (int seqNumber = 0; seqNumber < sequenceCount; seqNumber++) {
			Node sequenceNode = sequenceNodes.item(seqNumber);
			if (sequenceNode.getNodeType() != Node.ELEMENT_NODE)
				continue;

			Element sequenceElement = (Element) sequenceNode;
			String sequenceTitle = getTitle(sequenceElement, SEQUENCETITLE);

			Presentation presentation = PresentationFactory.getInstance().getPresentation(title, sequenceTitle);
			presentations.add(presentation);

			NodeList slideNumbers = sequenceElement.getElementsByTagName(SLIDENUMBER);
			int itemCount = slideNumbers.getLength();
			for (int itemNumber = 0; itemNumber < itemCount; itemNumber++) {
				Element slideNumber = (Element) slideNumbers.item(itemNumber);
				int slideNr = Integer.parseInt(slideNumber.getTextContent());

				// add referenced Slide to presentation
				Slide slide = slides.get(slideNr - 1);
				presentation.append(slide);
			}
		}
		return presentations;
	}
}