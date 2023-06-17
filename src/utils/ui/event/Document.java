package utils.ui.event;

import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Document {
	
    public static DocumentListener hidePassword(JPasswordField component){
        return new DocumentListener() {
            
			@Override
			public void insertUpdate(DocumentEvent e) {
				showPasswordDots();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				showPasswordDots();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				showPasswordDots();
			}

			private void showPasswordDots() {
				if (component.getPassword().length == 0) {
					component.setEchoChar((char) 0); 
				} else {
					component.setEchoChar('\u25CF'); 
				}
			}
		};
    }
}
