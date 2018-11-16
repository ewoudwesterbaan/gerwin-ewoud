package reader;

import java.util.ArrayList;
import java.util.List;

import factory.PresentationFactory;
import factory.SlideFactory;
import factory.SlideItemFactory;
import model.Presentation;
import model.Slide;

public class DemoReader implements Reader {

	@Override
	public List<Presentation> load() {
		List<Presentation> presentations = new ArrayList<Presentation>();
		
		SlideItemFactory sif = SlideItemFactory.getInstance();
		
		// create the demo slides
		Slide slide1 = SlideFactory.getInstance().getSlide("JabberPoint");
		slide1.append(sif.getSlideItem(1, "text", "Het Java Presentatie Tool"));
		slide1.append(sif.getSlideItem(2, "text", "Copyright (c) 1996-2000: Ian Darwin"));
		slide1.append(sif.getSlideItem(2, "text", "Copyright (c) 2000-now:"));
		slide1.append(sif.getSlideItem(2, "text", "Gert Florijn en Sylvia Stuurman"));
		slide1.append(sif.getSlideItem(4, "text", "JabberPoint aanroepen zonder bestandsnaam"));
		slide1.append(sif.getSlideItem(4, "text", "laat deze presentatie zien"));
		slide1.append(sif.getSlideItem(1, "text", "Navigeren:"));
		slide1.append(sif.getSlideItem(3, "text", "Volgende slide: PgDn of Enter"));
		slide1.append(sif.getSlideItem(3, "text", "Vorige slide: PgUp of up-arrow"));
		slide1.append(sif.getSlideItem(3, "text", "Stoppen: q or Q"));

		Slide slide2 = SlideFactory.getInstance().getSlide("Demonstratie van levels en stijlen");
		slide2.append(sif.getSlideItem(1, "text", "Level 1"));
		slide2.append(sif.getSlideItem(2, "text", "Level 2"));
		slide2.append(sif.getSlideItem(1, "text", "Nogmaals level 1"));
		slide2.append(sif.getSlideItem(1, "text", "Level 1 heeft stijl nummer 1"));
		slide2.append(sif.getSlideItem(2, "text", "Level 2 heeft stijl nummer 2"));
		slide2.append(sif.getSlideItem(3, "text", "Zo ziet level 3 er uit"));
		slide2.append(sif.getSlideItem(4, "text", "En dit is level 4"));

		Slide slide3 = SlideFactory.getInstance().getSlide("De derde slide");
		slide3.append(sif.getSlideItem(1, "text", "Om een nieuwe presentatie te openen,"));
		slide3.append(sif.getSlideItem(2, "text", "gebruik File->Open uit het menu."));
		slide3.append(sif.getSlideItem(1, "text", " "));
		slide3.append(sif.getSlideItem(1, "text", "Dit is het einde van de presentatie."));
		slide3.append(sif.getSlideItem(1, "image", "JabberPoint.jpg"));

		// create the presentations
		String showTitle = "Demo Presentation";
			
		Presentation presentation1 = PresentationFactory.getInstance().getPresentation(showTitle, "variant 1");
		presentation1.append(slide1);
		presentation1.append(slide2);
		presentation1.append(slide3);
		presentations.add(presentation1);

		Presentation presentation2 = PresentationFactory.getInstance().getPresentation(showTitle, "variant 2");
		presentation2.append(slide2);
		presentation2.append(slide1);
		presentation2.append(slide3);
		presentations.add(presentation2);

		return presentations;
	}
}