package utils.ui.event;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class Focus {
	
	public enum componentType{ 
		EMAIL, PASSSWORD, TEXT
	}

    public static FocusListener setPlaceholder(JTextField component,String placeholder){
        return new FocusListener() {
            @Override
			public void focusGained(FocusEvent e) {
				if (component.getText().equals(placeholder)) {
					component.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (component.getText().isEmpty()) {
					component.setForeground(Color.GRAY);
					component.setText(placeholder);
				}
			}
        };
    } 


}
