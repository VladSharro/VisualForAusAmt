package com.example.thelasthope;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Route("application")
public class ApplicationView extends VerticalLayout {

    private Map<Tab, VerticalLayout> tabsToPages = new HashMap<>();

    public ApplicationView() throws IOException {
        setAlignItems(Alignment.CENTER);

        Tabs tabs = new Tabs();

        Tab passportTab = new Tab("Passport");
        PassportClass passportContent = new PassportClass();

        Tab familyTab = new Tab("Family");
        FamilyClass familyContent = new FamilyClass();

        Tab recidenceTab = new Tab("Residence");
        ResidenceClass recidenceContent = new ResidenceClass();

        Tab visaTab = new Tab("Visa");
        VisaClass visaContent = new VisaClass();

        Tab supportTab = new Tab("Support");
        SupportClass supportContent = new SupportClass();

        Tab insuranceTab = new Tab("Support");
        InsuranceClass insuranceContent = new InsuranceClass();

        Tab OffenceTab = new Tab("Offence");
        OffencesClass OffenceContent = new OffencesClass();

        Tab PhotoTab = new Tab("Photo");
        PhotoClass PhotoContent = new PhotoClass();

        tabs.add(passportTab, familyTab, recidenceTab, visaTab, supportTab, insuranceTab, OffenceTab, PhotoTab);

        tabsToPages.put(passportTab, passportContent);
        tabsToPages.put(familyTab, familyContent);
        tabsToPages.put(recidenceTab, recidenceContent);
        tabsToPages.put(visaTab, visaContent);
        tabsToPages.put(supportTab, supportContent);
        tabsToPages.put(insuranceTab, insuranceContent);
        tabsToPages.put(OffenceTab, OffenceContent);
        tabsToPages.put(PhotoTab, PhotoContent);




        tabs.setSelectedTab(passportTab);

        familyContent.setVisible(false);
        recidenceContent.setVisible(false);
        visaContent.setVisible(false);
        supportContent.setVisible(false);
        insuranceContent.setVisible(false);
        OffenceContent.setVisible(false);
        PhotoContent.setVisible(false);





        tabs.addSelectedChangeListener(event -> {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            VerticalLayout selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });

        tabs.setSelectedTab(passportTab);
        familyContent.setVisible(false); // Скрываем содержимое вкладки "Family"

        tabs.addSelectedChangeListener(event -> {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            VerticalLayout selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);

            // Устанавливаем информацию о выбранной вкладке в сессии
            UI.getCurrent().getSession().setAttribute("selectedTab", tabs.getSelectedTab().getLabel());
        });

        tabs.setSelectedTab(passportTab);
        familyContent.setVisible(false); // Скрываем содержимое вкладки "Family"


        HorizontalLayout tabsLayout = new HorizontalLayout(tabs);
        tabsLayout.setWidthFull(); // Устанавливаем полную ширину для растягивания макета
        tabsLayout.setJustifyContentMode(JustifyContentMode.CENTER); // Центрируем вкладки в макете

        add(tabsLayout);
        tabsToPages.values().forEach(this::add);
    }
}
