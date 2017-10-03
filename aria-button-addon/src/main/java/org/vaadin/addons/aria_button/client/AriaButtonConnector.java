package org.vaadin.addons.aria_button.client;

import org.vaadin.addons.aria_button.AriaButton;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VButton;
import com.vaadin.client.ui.button.ButtonConnector;
import com.vaadin.shared.ui.Connect;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(AriaButton.class)
public class AriaButtonConnector extends AbstractExtensionConnector {
    VButton button;

    AriaButtonServerRpc rpc = RpcProxy.create(AriaButtonServerRpc.class, this);

    public AriaButtonConnector() {
    }

    @Override
    public AriaButtonState getState() {
        return (AriaButtonState) super.getState();
    }

    @Override
    protected void extend(ServerConnector target) {
        target.addStateChangeHandler(new StateChangeEvent.StateChangeHandler() {
            private static final long serialVersionUID = -8439729365677484553L;

            @Override
            public void onStateChanged(StateChangeEvent stateChangeEvent) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {

                    @Override
                    public void execute() {
                        button = ((ButtonConnector) target).getWidget();
                        Element buttonEl = button.getElement();
                        String ariaLabel = getState().ariaLabel;
                        String ariaLabelledBy = getState().ariaLabelledBy;
                        String ariaDescribedBy = getState().ariaDescribedBy;

                        if (ariaLabelledBy != null
                                && !ariaLabelledBy.isEmpty()) {
                            buttonEl.setAttribute("aria-labelledby",
                                    ariaLabelledBy);
                            buttonEl.removeAttribute("aria-label");
                            getState().ariaLabel = "";
                        } else if (ariaLabel != null && !ariaLabel.isEmpty()) {
                            buttonEl.setAttribute("aria-label", ariaLabel);
                            buttonEl.removeAttribute("aria-labelledby");
                            getState().ariaLabelledBy = "";
                        }
                        if (ariaDescribedBy != null
                                && !ariaDescribedBy.isEmpty()) {
                            addAriaDescribedBy(buttonEl, ariaDescribedBy);
                        }
                    }
                });
            }

        });
    }

    private void addAriaDescribedBy(Element buttonEl, String ariaText) {
        Element div = DOM.createDiv();
        div.setId("desc-" + HTMLPanel.createUniqueId());
        div.setInnerText(ariaText);
        div.getStyle().setDisplay(Display.NONE);
        div.getStyle().setVisibility(Visibility.HIDDEN);
        buttonEl.setAttribute("aria-describedby", div.getId());
        buttonEl.getParentElement().appendChild(div);
    }
}
