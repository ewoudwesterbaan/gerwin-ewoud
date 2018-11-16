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
import model.BitmapItem;
import model.Presentation;
import model.Slide;
import model.SlideItem;
import model.SlideSequence;
import model.TextItem;

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

	public XMLReader(String filename) {
		this.filename = filename;
	}

	@Override
	public List<Presentation> load() {
		return loadPresentations(this.filename);
	}

	private String getTitle(Element element, String tagName) {
		NodeList titles = element.getElementsByTagName(tagName);
		return titles.item(0).getTextContent();
	}

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

			// process all slide items
			List<SlideItem> slideItems = new ArrayList<SlideItem>();
			NodeList slideItemNodes = slideElement.getElementsByTagName(ITEM);
			int itemCount = slideItemNodes.getLength();
			for (int itemNumber = 0; itemNumber < itemCount; itemNumber++) {
				Element item = (Element) slideItemNodes.item(itemNumber);
				SlideItem slideItem = createSlideItem(item);
				slideItems.add(slideItem);
			}
 
			Slide slide = SlideFactory.getInstance().getSlide(title, slideItems);
			slides.add(slide);
		}
		return slides;
	}

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
		// deze test skippen hier? komt overeen met de test in SlideItemFactory...
		SlideItem slideItem = null;
		if (TEXT.equals(type)) {
			slideItem = SlideItemFactory.getInstance().getSlideItem(level, "text", item.getTextContent());
		} else {
			if (IMAGE.equals(type)) {
				slideItem = SlideItemFactory.getInstance().getSlideItem(level, "image", item.getTextContent());
			} else {
				System.err.println(UNKNOWNTYPE);
			}
		}

		return slideItem;
	}

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