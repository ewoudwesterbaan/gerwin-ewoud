package reader;

import java.util.ArrayList;
import java.util.List;

import model.BitmapItem;
import model.Presentation;
import model.Slide;
import model.SlideSequence;

public class DemoReader implements Reader {

	@Override
	public List<Presentation> load() {
		List<Presentation> presentations = new ArrayList<Presentation>();

		// create the demo slides
		Slide slide1 = new Slide();
		slide1.setTitle("JabberPoint");
		slide1.append(1, "Het Java Presentatie Tool");
		slide1.append(2, "Copyright (c) 1996-2000: Ian Darwin");
		slide1.append(2, "Copyright (c) 2000-now:");
		slide1.append(2, "Gert Florijn en Sylvia Stuurman");
		slide1.append(4, "JabberPoint aanroepen zonder bestandsnaam");
		slide1.append(4, "laat deze presentatie zien");
		slide1.append(1, "Navigeren:");
		slide1.append(3, "Volgende slide: PgDn of Enter");
		slide1.append(3, "Vorige slide: PgUp of up-arrow");
		slide1.append(3, "Stoppen: q or Q");

		Slide slide2 = new Slide();
		slide2.setTitle("Demonstratie van levels en stijlen");
		slide2.append(1, "Level 1");
		slide2.append(2, "Level 2");
		slide2.append(1, "Nogmaals level 1");
		slide2.append(1, "Level 1 heeft stijl nummer 1");
		slide2.append(2, "Level 2 heeft stijl nummer 2");
		slide2.append(3, "Zo ziet level 3 er uit");
		slide2.append(4, "En dit is level 4");

		Slide slide3 = new Slide();
		slide3.setTitle("De derde slide");
		slide3.append(1, "Om een nieuwe presentatie te openen,");
		slide3.append(2, "gebruik File->Open uit het menu.");
		slide3.append(1, " ");
		slide3.append(1, "Dit is het einde van de presentatie.");
		slide3.append(new BitmapItem(1, "JabberPoint.jpg"));

		// create the presentations
		Presentation presentation1 = new SlideSequence();
		presentation1.setTitle("Demo Presentation - variant 1");
		presentation1.append(slide1);
		presentation1.append(slide2);
		presentation1.append(slide3);
		presentations.add(presentation1);

		Presentation presentation2 = new SlideSequence();
		presentation2.setTitle("Demo Presentation - variant 2");
		presentation2.append(slide2);
		presentation2.append(slide1);
		presentation2.append(slide3);
		presentations.add(presentation2);

		return presentations;
	}
}