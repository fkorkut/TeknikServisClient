package egitim.uniyaz.components;

import com.vaadin.ui.VerticalLayout;

public class Container extends VerticalLayout {
    public Container() {

        setWidth(100,Unit.PERCENTAGE);

        Content content = new Content();

        Menu menu = new Menu(content);
        addComponent(menu);

        addComponent(content);
    }
}