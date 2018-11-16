package reader;

import java.util.ArrayList;
import java.util.List;

import factory.PresentationFactory;
import factory.SlideFactory;
import factory.SlideItemFactory;
import model.Presentation;
import model.Slide;
import model.SlideItem;

public class DemoReader implements Reader {

	@Override
	public List<Presentation> load() {
		List<Presentation> presentations = new ArrayList<Presentation>();

		List<SlideItem> slideItems = new ArrayList<SlideItem>();
		
		// create the demo slides		
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(1, "text", "Het Java Presentatie Tool"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(2, "text", "Copyright (c) 1996-2000: Ian Darwin"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(2, "text", "Copyright (c) 2000-now:"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(2, "text", "Gert Florijn en Sylvia Stuurman"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(4, "text", "JabberPoint aanroepen zonder bestandsnaam"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(4, "text", "laat deze presentatie zien"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(1, "text", "Navigeren:"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(3, "text", "Volgende slide: PgDn of Enter"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(3, "text", "Vorige slide: PgUp of up-arrow"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(3, "text", "Stoppen: q or Q"));
		Slide slide1 = SlideFactory.getInstance().getSlide("JabberPoint", slideItems);
		
		slideItems.clear();

		slideItems.add(SlideItemFactory.getInstance().getSlideItem(1, "text", "Level 1"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(2, "text", "Level 2"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(1, "text", "Nogmaals level 1"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(1, "text", "Level 1 heeft stijl nummer 1"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(2, "text", "Level 2 heeft stijl nummer 2"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(3, "text", "Zo ziet level 3 er uit"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(4, "text", "En dit is level 4"));
		Slide slide2 = SlideFactory.getInstance().getSlide("Demonstratie van levels en stijlen", slideItems);
		
		slideItems.clear();
		
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(1, "text", "Om een nieuwe presentatie te openen,"));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(2, "text", "gebruik File->Open uit het menu."));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(1, "text", " "));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(1, "text", "Dit is het einde van de presentatie."));
		slideItems.add(SlideItemFactory.getInstance().getSlideItem(1, "image", "JabberPoint.jpg"));
		Slide slide3 = SlideFactory.getInstance().getSlide("De derde slide", slideItems);
		
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