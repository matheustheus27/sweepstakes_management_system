package Rules.Controllers;

import java.util.HashMap;
import java.util.Map;

import Rules.Objects.Rule;

public class RuleController {
    //#region VARIABLES
    private Map<String, Rule> rules;
    //#endregion

    //#region CONSTRUCTOR
    public RuleController() {
        this.rules = new HashMap<>();
    }
    //#endregion

    //#region FUNCTIONS
    public void registerRule(Rule rule) {
        rules.put(rule.getId(), rule);
    }

    public Rule findRule(String id) {
        return rules.get(id);
    }

    public void listRules() {
        for (Rule rule : rules.values()) {
            rule.displayInformation();
        }
    }

    public void removeRule(String id) {
        rules.remove(id);
    }
    //#endregion
}
