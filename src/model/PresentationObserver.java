package model;

/**
 * Observer voor het ontvangen van een notification wanneer een andere
 * {@link Slide} geactiveerd is. PresentationObserver objecten moeten zich
 * aanmelden bij de {@link Presenter}.
 * 
 * @author Gerwin van Dijken
 * @author Ewoud Westerbaan
 * @since 2.0
 * @version 2.0 2018/11/18 Gerwin van Dijken en Ewoud Westerbaan
 *
 */
public interface PresentationObserver {
	/**
	 * Notification dat wordt aangeroepen zodra een andere {@link Slide}
	 * actief is.
	 */
	void update(Slide slide);
}