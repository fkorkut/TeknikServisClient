package egitim.uniyaz;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import egitim.uniyaz.views.KullaniciGirisView;

import javax.servlet.annotation.WebServlet;


@Theme("mytheme")
@Widgetset("egitim.uniyaz.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        KullaniciGirisView kullaniciGirisView = new KullaniciGirisView();
        setContent(kullaniciGirisView);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
