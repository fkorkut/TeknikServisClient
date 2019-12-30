package egitim.uniyaz.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import egitim.uniyaz.views.ArizaEkleView;
import egitim.uniyaz.views.ArizaListeleView;

public class Menu extends HorizontalLayout {
    private Content content;

    private MenuButton arizaEkle;
    private MenuButton arizaListele;

    public Menu(Content content) {
        setHeight(100, Unit.PIXELS);
        setWidth(100, Unit.PERCENTAGE);
        this.content=content;

        setSpacing(true);
        setMargin(true);
        createButtons();
    }

    private void createButtons() {
        arizaEkle = new MenuButton(FontAwesome.PLUS);
        arizaEkle.setCaption("Arıza Ekle");
        arizaEkle.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ArizaEkleView arizaEkleView = new ArizaEkleView();
                content.setContent(arizaEkleView);
            }
        });
        addComponent(arizaEkle);

        arizaListele = new MenuButton(FontAwesome.EXCHANGE);
        arizaListele.setCaption("Arıza Listele - Güncelle");
        arizaListele.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ArizaListeleView arizaListeleView = new ArizaListeleView();
                content.setContent(arizaListeleView);
            }
        });
        addComponent(arizaListele);

    }
}
