package club.j3studios.system.utils;

import javax.swing.JComboBox;

public class ComboBoxSuggestion<E> extends JComboBox<E> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComboBoxSuggestion() {
        setUI(new ComboSuggestionUI());
    }
}