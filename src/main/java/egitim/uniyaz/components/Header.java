package egitim.uniyaz.components;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Header extends VerticalLayout {

    private Label baslik;

    public Header() {
        setHeight(100, Unit.PIXELS);
        setWidth(100, Unit.PERCENTAGE);

        baslik = new Label();
        baslik.setValue("Teknik Servis");
        baslik.addStyleName(ValoTheme.LABEL_H1);
        baslik.addStyleName(ValoTheme.TEXTAREA_ALIGN_CENTER);
        addComponent(baslik);

    }
    public void setHeaderTitle(String title) {
        baslik.setValue(title);
    }
}