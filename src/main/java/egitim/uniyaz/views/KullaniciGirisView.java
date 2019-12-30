package egitim.uniyaz.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import egitim.uniyaz.MyUI;
import egitim.uniyaz.components.General;
import egitim.uniyaz.dto.KullaniciDto;
import egitim.uniyaz.rest.ResetClientKullanici;

public class KullaniciGirisView extends VerticalLayout{

    @PropertyId("isim")
    private TextField isimTextField;

    @PropertyId("sifre")
    private PasswordField sifreTextField;

    private Button girisButon;

    private FormLayout formLayout;

    private FieldGroup binder;
    private BeanItem<KullaniciDto> item;
    public static KullaniciDto kullaniciDto = new KullaniciDto();

    public KullaniciGirisView() {
        fillLayout();
        fillViewKullanici(new KullaniciDto());

    }


    private void fillViewKullanici(KullaniciDto kullaniciDto) {
        item = new BeanItem<KullaniciDto>(kullaniciDto);
        binder = new FieldGroup(item);
        binder.bindMemberFields(this);
    }

    private void fillLayout()  {
        formLayout = new FormLayout();
        formLayout.setMargin(true);
        formLayout.addStyleName("outlined");
        formLayout.setSizeFull();


        isimTextField = new TextField();
        isimTextField.setCaption("Ad");
        isimTextField.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);
        formLayout.addComponent(isimTextField);

        sifreTextField = new PasswordField();
        sifreTextField.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);
        sifreTextField.setCaption("Şifre");
        formLayout.addComponent(sifreTextField);

        girisButon = new Button();
        girisButon.setCaption("Giriş");
        girisButon.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                girisKullanici();
                if (kullaniciDto == null){
                    Notification.show("Kullanıcı Kayıtlı değil !!");

                }else{
                    General general = new General(kullaniciDto);
                    MyUI.getCurrent().setContent(general);
                }
           }

        });
        formLayout.addComponent(girisButon);

        addComponent(formLayout);


    }
    private KullaniciDto girisKullanici() {
        try {
            binder.commit();
            KullaniciDto kullaniciDto = item.getBean();

            ResetClientKullanici resetClientKullanici = new ResetClientKullanici();
            kullaniciDto = resetClientKullanici.kullaniciListe(kullaniciDto);

            return kullaniciDto;

        }
        catch (FieldGroup.CommitException e) {
            System.out.println(e.getMessage());
            return  null;
        }
    }


}