package egitim.uniyaz.views;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import egitim.uniyaz.dto.ArizaDto;
import egitim.uniyaz.dto.ArizaDurum;
import egitim.uniyaz.rest.RestClientAriza;

import java.util.Date;
import java.util.List;

public class ArizaListeleView extends VerticalLayout {

    private Table table;
    private IndexedContainer indexedContainer;

    private TextField idField;
    private TextField musteriTcText;
    private TextField musteriIsimText;
    private TextField musteriSoyisimText;
    private TextField musteriIdText;
    private TextField icerikText;
    private ComboBox arizaDurumCombo;
    private DateField baslangicTarihi;

    private ArizaDto arizaDto;

    private FormLayout formLayout;

    public ArizaListeleView() {
        createLayout();
        searchCustomer();
        allCustomers();
        createFields();
        updateButton();
        addComponent(formLayout);
    }

    private void createLayout() {
        formLayout = new FormLayout();
        formLayout.setMargin(true);
        formLayout.addStyleName("outlined");
        formLayout.setSizeFull();

    }

    private  void createTable() {

        table = new Table();
        table.setSelectable(true);

        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id",Long.class,null);
        indexedContainer.addContainerProperty("musteriId",Long.class,null);
        indexedContainer.addContainerProperty("musteriIsım",String.class,null);
        indexedContainer.addContainerProperty("musteriTC",String.class,null);
        indexedContainer.addContainerProperty("icerik",String.class,null);
        indexedContainer.addContainerProperty("arizaDurum",Enum.class,null);
        indexedContainer.addContainerProperty("baslangicTarihi", Date.class,null);

        table.setContainerDataSource(indexedContainer);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                ArizaDto arizaDto = (ArizaDto) itemClickEvent.getItemId();
                fillFields(arizaDto);
            }
        });
        table.setPageLength(table.size());
        formLayout.addComponent(table);


    }

    private void fillTable(List<ArizaDto> arizaDtoList) {

        for (ArizaDto arizaDto1 : arizaDtoList) {

            Item item = indexedContainer.addItem(arizaDto1);

            item.getItemProperty("id").setValue(arizaDto1.getId());
            item.getItemProperty("musteriId").setValue(arizaDto1.getMusteriId());
            item.getItemProperty("musteriIsım").setValue(arizaDto1.getMusteriIsim());
            item.getItemProperty("musteriTC").setValue(arizaDto1.getMusteriTC());
            item.getItemProperty("icerik").setValue(arizaDto1.getIcerik());
            item.getItemProperty("arizaDurum").setValue(arizaDto1.getArizaDurum());
            item.getItemProperty("baslangicTarihi").setValue(arizaDto1.getBaslangicTarihi());

        }
    }

    private void searchCustomer() {
        TextField aramaText = new TextField();
        aramaText.setDescription("Müşteri Adı");
        aramaText.setCaption("Müşteri Adı");
        formLayout.addComponent(aramaText);

        Button araBtn = new Button();
        araBtn.setCaption("Ara");
        araBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                createTable();
                RestClientAriza restClientAriza = new RestClientAriza();
                List<ArizaDto> arizaDtoList = restClientAriza.searchAriza(aramaText.getValue());
                fillTable(arizaDtoList);
            }
        });
        formLayout.addComponent(araBtn);
    }

    private void allCustomers(){
        createTable();
        RestClientAriza restClientAriza = new RestClientAriza();
        List<ArizaDto> arizaDtoList = restClientAriza.arizaListe();
        fillTable(arizaDtoList);
    }

    private void createFields() {
        idField = new TextField();
        idField.setEnabled(false);
        idField.setCaption("Ariza Id");
        formLayout.addComponent(idField);

        musteriIdText = new TextField();
        musteriIdText.setCaption("Müşteri Id");
        formLayout.addComponent(musteriIdText);

        musteriIsimText = new TextField();
        musteriIsimText.setCaption("Müşteri Isim");
        formLayout.addComponent(musteriIsimText);

        musteriSoyisimText = new TextField();
        musteriSoyisimText.setCaption("Müşteri Soyisim");
        formLayout.addComponent(musteriSoyisimText);

        musteriTcText = new TextField();
        musteriTcText.setCaption("Müşteri TC no");
        formLayout.addComponent(musteriTcText);

        icerikText = new TextField();
        icerikText.setCaption("İçerik");
        formLayout.addComponent(icerikText);

        arizaDurumCombo = new ComboBox();
        arizaDurumCombo.addItem(ArizaDurum.GIDERILDI);
        arizaDurumCombo.addItem(ArizaDurum.GIDERILEMEDI);
        arizaDurumCombo.setCaption("Arıza Durumu");
        formLayout.addComponent(arizaDurumCombo);

        baslangicTarihi = new DateField();
        baslangicTarihi.setCaption("Başlangıç Tarihi");
        formLayout.addComponent(baslangicTarihi);
    }

    private void fillFields(ArizaDto arizaDto) {
        idField.setValue(String.valueOf(arizaDto.getId()));
        musteriIdText.setValue(String.valueOf(arizaDto.getMusteriId()));
        musteriIsimText.setValue(String.valueOf(arizaDto.getMusteriIsim()));
        musteriSoyisimText.setValue(String.valueOf(arizaDto.getMusteriSoyisim()));
        musteriTcText.setValue(String.valueOf(arizaDto.getMusteriTC()));
        icerikText.setValue(String.valueOf(arizaDto.getIcerik()));
        arizaDurumCombo.select(arizaDto.getArizaDurum());
        baslangicTarihi.setValue(arizaDto.getBaslangicTarihi());

    }

    private  void updateButton(){
        Button button = new Button();
        button.setCaption("Güncelle");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                arizaDto = new ArizaDto();
                updateValue();
                RestClientAriza restClientAriza = new RestClientAriza();
                restClientAriza.saveAriza(arizaDto);
                Notification.show("Güncelleme başarılı");
            }
        });
        formLayout.addComponent(button);
    }

    private void updateValue(){
        arizaDto.setId(Long.valueOf(idField.getValue()));
        arizaDto.setMusteriId(Long.valueOf(musteriIdText.getValue()));
        arizaDto.setMusteriIsim(musteriIsimText.getValue());
        arizaDto.setMusteriSoyisim(musteriSoyisimText.getValue());
        arizaDto.setMusteriTC(musteriTcText.getValue());
        arizaDto.setIcerik(icerikText.getValue());
        arizaDto.setArizaDurum((ArizaDurum) arizaDurumCombo.getValue());
        arizaDto.setBaslangicTarihi(baslangicTarihi.getValue());

    }
}

