package org.vaadin.addons.aria_button.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.aria_button.AriaButton;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("AriaButton Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        HorizontalLayout hl = new HorizontalLayout();
        // Initialize our new UI component
        final Button xButton = new Button("X");
        AriaButton.extendAriaLabel(xButton, "Horizontal");

        final Button yButton = new Button("Y");
        AriaButton.extendAriaLabel(yButton, "Vertical");
        AriaButton.extendAriaDescribedBy(yButton,
                "The Y-axis is the vertical axis");

        final Button openButton = new Button("Open");
        AriaButton.extendAriaLabelledBy(openButton, "Open");
        AriaButton.extendAriaDescribedBy(openButton,
                "This is a button for opening");

        hl.addComponents(xButton, yButton, openButton);
        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        Label label = new Label("Buttons with aria support");
        layout.addComponents(label, hl);
        layout.setComponentAlignment(label, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(hl, Alignment.TOP_CENTER);
        setContent(layout);
    }
}
