package org.vaadin.addons.aria_button;

import org.vaadin.addons.aria_button.client.AriaButtonServerRpc;
import org.vaadin.addons.aria_button.client.AriaButtonState;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.Button;

/**
 * AriaButton is an extension for adding custom accessibility texts
 * (<code>aria-label, aria-labelledby, aria-description</code> to
 * {@link com.vaadin.ui.Button}
 *
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/Accessibility/ARIA/ARIA_Techniques">ARIA Documentation</a>
 * for detailed information about ARIA accessibility technology
 */
public class AriaButton extends AbstractExtension {
    private AriaButtonServerRpc rpc = new AriaButtonServerRpc() {
    };

    private AriaButton() {
        registerRpc(rpc);
    }

    /**
     * Sets <code>aria-label</code> attribute to specified button.<br>
     * <br>
     *
     * User agents give precedence to <code>aria-labelledby</code> over
     * <code>aria-label</code> when computing the accessible name property.
     * Long texts are not recommended, {@link #extendAriaDescribedBy(Button, String)}
     * should be used for more verbose information.
     *
     * @param button
     *            Button
     * @param ariaLabel
     *            String text presented in aria-label
     */
    public static void extendAriaLabel(Button button, String ariaLabel) {
        AriaButton b = new AriaButton();
        b.getState().ariaLabel = ariaLabel;
        b.getState().ariaLabelledBy = "";
        b.extend(button);
    }

    /**
     * Sets <code>aria-labelledby</code> attribute to specified button.<br>
     * <br>
     *
     * User agents give precedence to <code>aria-labelledby</code> over
     * <code>aria-label</code> when computing the accessible name property.
     * Long texts are not recommended, {@link #extendAriaDescribedBy(Button, String)}
     * should be used for more verbose information.
     *
     * @param button
     *            Button
     * @param ariaLabelledBy
     *            String text presented in aria-labelledby
     */
    public static void extendAriaLabelledBy(Button button,
            String ariaLabelledBy) {
        AriaButton b = new AriaButton();
        b.getState().ariaLabelledBy = ariaLabelledBy;
        b.getState().ariaLabel = "";
        b.extend(button);
    }

    /**
     * Sets <code>aria-describedby</code> attribute to specified button.<br>
     * Description can be long.
     *
     * @param button
     *            Button
     * @param ariaDescribedBy
     *            String text presented in aria-describedby
     */
    public static void extendAriaDescribedBy(Button button,
            String ariaDescribedBy) {
        AriaButton b = new AriaButton();
        b.getState().ariaDescribedBy = ariaDescribedBy;
        b.extend(button);
    }

    @Override
    protected AriaButtonState getState() {
        return (AriaButtonState) super.getState();
    }

}
