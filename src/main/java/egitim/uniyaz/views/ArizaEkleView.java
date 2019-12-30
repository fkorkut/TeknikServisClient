package egitim.uniyaz.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import egitim.uniyaz.dto.ArizaDto;
import egitim.uniyaz.dto.ArizaDurum;
import egitim.uniyaz.rest.RestClientAriza;

public class ArizaEkleView extends VerticalLayout {

    @PropertyId("id")
    TextField idField;

    @PropertyId("musteriTC")
    TextField musteriTcText;

    @PropertyId("musteriIsim")
    TextField musteriIsimText;

    @PropertyId("musteriSoyisim")
    TextField musteriSoyisimText;

    @PropertyId("musteriId")
    TextField musteriIdText;


    @PropertyId("icerik")
    TextField icerikText;

    @PropertyId("arizaDurum")
    ComboBox arizaDurumCombo;

    @PropertyId("baslangicTarihi")
    DateField baslangicTarihi;

    FormLayout formLayout = new FormLayout();
    private FieldGroup binder;
    private BeanItem<ArizaDto> item;
   private ArizaDurum arizaDurum;

    public ArizaEkleView() {
        fillLayout();
        fillViewAriza(new ArizaDto());
    }

    private void fillLayout() {

        formLayout=new FormLayout();
        formLayout.setMargin(true);
        formLayout.addStyleName("outlined");
        formLayout.setSizeFull();

        idField = new TextField("Id");
        idField.setEnabled(false);
        formLayout.addComponent(idField);

        musteriTcText = new TextField();
        musteriTcText.setDescription("Müşteri TC");
        musteriTcText.setCaption("Müşteri TC");
        formLayout.addComponent(musteriTcText);


        musteriIsimText = new TextField();
        musteriIsimText.setDescription("Müşteri İsim");
        musteriIsimText.setCaption("Müşteri İsim");
        formLayout.addComponent(musteriIsimText);

        musteriSoyisimText = new TextField();
        musteriSoyisimText.setDescription("Müşteri Soyisim");
        musteriSoyisimText.setCaption("Müşteri Soyisim");
        formLayout.addComponent(musteriSoyisimText);



        musteriIdText = new TextField();
        musteriIdText.setDescription("Müşteri id");
        musteriIdText.setCaption("Müşteri id");
        formLayout.addComponent(musteriIdText);


        icerikText = new TextField();
        icerikText.setDescription("İçerik");
        icerikText.setCaption("İçerik");
        formLayout.addComponent(icerikText);

        arizaDurumCombo = new ComboBox("ArizaDurum");
        arizaDurumCombo.addItem(ArizaDurum.GIDERILDI);
        arizaDurumCombo.addItem(ArizaDurum.GIDERILEMEDI);
        formLayout.addComponent(arizaDurumCombo);

        baslangicTarihi= new DateField();
        formLayout.addComponent(baslangicTarihi);


        Button ekleBtn = new Button();
        ekleBtn.setCaption("Ekle");
        ekleBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                arizaEkle();
            }
        });
        formLayout.addComponent(ekleBtn);

        addComponent( formLayout);
    }

    private void fillViewAriza(ArizaDto arizaDto) {
        item = new BeanItem<ArizaDto>(arizaDto);
        binder = new FieldGroup(item);
        binder.bindMemberFields(this);
    }

    private void arizaEkle() {
        try {
            binder.commit();

            ArizaDto arizaDto = item.getBean();

            RestClientAriza restClientAriza = new RestClientAriza();
            restClientAriza.saveAriza(arizaDto);
            Notification.show("İşlem Başarılı");


        } catch (FieldGroup.CommitException e) {
            System.out.println(e.getMessage());

        }
    }

}