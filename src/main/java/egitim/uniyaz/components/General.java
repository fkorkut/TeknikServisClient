package egitim.uniyaz.components;

import com.vaadin.ui.VerticalLayout;
import egitim.uniyaz.dto.KullaniciDto;

public class General extends VerticalLayout {
    public General(KullaniciDto kullaniciDto) {

        Header header = new Header();
        addComponent(header);


         Container container = new Container();
         addComponent(container);

    }
}
