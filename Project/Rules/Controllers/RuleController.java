package Rules.Controllers;

import java.util.Map;

import Database.Database;
import Response.Response;
import Rules.Objects.Rule;

public class RuleController {
    //#region VARIABLES
    private Map<String, Rule> rules;
    //#endregion

    //#region CONSTRUCTOR
    public RuleController() {
        this.rules = Database.getRules();
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

    public Response checkForRule() {
        if(this.rules.isEmpty()) {return Response.NOT_FOUND;}

        return Response.OK;
    }
    //#endregion
}
