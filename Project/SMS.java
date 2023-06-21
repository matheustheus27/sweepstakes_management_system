import java.util.ArrayList;
import java.util.Scanner;

import Items.Controllers.ItemController;
import Items.Objects.Item;
import Response.Response;
import Sweepstakes.Controllers.SweepstakeController;
import Sweepstakes.Objects.Sweepstake;
import Sweepstakes.Objects.Sweepstake.Status;
import Users.Controllers.CommonController;
import Users.Controllers.ManagerController;
import Users.Controllers.UserController;
import Users.Objects.Common;
import Users.Objects.Manager;

public class SMS {
    //#region VARIABLES
    private UserController userController;
    private ManagerController managerController;
    private CommonController commonController;
    private SweepstakeController sweepstakeController;
    private ItemController itemController;
    private  Scanner scanner;
    //#endregion

    //#region CONSTRUCTORS
    public SMS() {
        this.userController = new UserController();

        this.sweepstakeController = new SweepstakeController();
        this.itemController = new ItemController();
        this.scanner = new Scanner(System.in);
    }
    //#region

    //#region MAIN
    public void index() {
        int option;
        
        do {
            System.out.println("----- SMS - Sistema de Gerenciamento de Sorteios -----");
            System.out.println("1. Login/Cadastro de Usuarios");
            System.out.println("2. Sair");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    auth();
                break;
                case 2:
                    System.exit(0);
                break;
                default:
                    System.out.println("Opção Não Reconhecida.");
                break;
            }
        } while (option != 2);
    }
    //#endregion

    //#region AUTHENTICATION
    public void auth() {
        int opcao;
        Response response = Response.NOT_FOUND;

        System.out.println("----- SMS - Sistema de Gerenciamento de Sorteios -----");
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.println("0. Pagina Inicial");

        opcao = scanner.nextInt();
        scanner.nextLine();

        if(opcao == 1) {
            response = login();
        } else if(opcao == 2) {
            response = singnup();
        } else {
            index();
        }

        panel(response);
    }

    public Response login() {  
              
        System.out.println("----- SMS - Tela de Login -----");

        System.out.println("Digite o CPF:");
        String cpf = scanner.next();

        System.out.println("Digite a Senha:");
        String password = scanner.next();

        return userController.logIn(cpf, password);
    }

    public Response singnup() {
        System.out.println("----- SMS - Cadastro de Usuario -----");
        System.out.println("Digite o Nome:");
        String name = scanner.nextLine();

        System.out.println("Digite o CPF:");
        String cpf = scanner.next();

        System.out.println("Digite a Senha:");
        String password = scanner.next();

        System.out.println("Digite o Hash em caso de Usuario Admin:");
        String hashValidate = scanner.next();

        return userController.singnUp(cpf, name, password, hashValidate);
    }
    //#endregion

    //#region PANEL ACCESS
    private void panel(Response response) {
        System.out.println(response.getMessage());

        switch (response) {
            case MANAGER_ACCESS:
                managerController = new ManagerController((Manager) userController.getSessionUser());
                panelManager();
            break;
            case COMMON_ACCESS:
                commonController = new CommonController((Common) userController.getSessionUser());
                panelCommon();
            break;
            default:
                auth();
            break;
        }
    }

    private void panelManager() {
        int option;
        do {
            System.out.println("----- SMS - Painel de Administrador -----");
            System.out.println("1. Listar Sorteios");
            System.out.println("2. Listar Itens");
            System.out.println("3. Analisar Sorteio");
            System.out.println("4. Analisar Item");
            System.out.println("0. Sair");

            option = scanner.nextInt();

            switch(option) {
                case 1:
                    listSweepstakes();
                break;
                case 2:
                    listItems();
                break;
                case 3:
                    analyseSweepstake();
                break;
                case 4:
                    analyseItem();
                break;
            }
        } while(option != 0);
    }

    private void panelCommon() {
        int option;
        do {
            System.out.println("----- SMS - Painel de Administrador -----");
            System.out.println("1. Cadastrar Sorteio");
            System.out.println("2. Cadastrar Item");
            System.out.println("3. Listar Meus Sorteios");
            System.out.println("4. Listar Meus Itens");
            System.out.println("5. Listar Sorteios Participante");
            System.out.println("6. Listar Sorteios Disponiveis");
            System.out.println("7. Participar de Sorteio");
            System.out.println("8. Sortear");
            System.out.println("0. Sair");

            option = scanner.nextInt();

            switch(option) {
                case 1:
                    registerSweepstake();
                break;
                case 2:
                    registerItem();
                break;
                case 3:
                    listSweepstakesPerUser();
                break;
                case 4:
                    listItemsPerUser();;
                break;
                case 5:
                    listSweepstakesPerEntrant();
                break;
                case 6:
                    listSweepstakes();
                break;
                case 7:
                    joinSweepstake();
                break;
                case 8:
                    makeSweepstake();
                break;
            }
        } while(option != 0);
    }
    //#endregion

    //#region FUNCTIONS SWEEPSTAKES
    private void listSweepstakesPerUser() {
        this.commonController.listSweepstakes();
    }

    private void listSweepstakesPerEntrant() {
        this.sweepstakeController.listSweepstakesPerEntrant(userController.getSessionUser().getId());
    }

    private void listSweepstakes() {
        System.out.println("----- SMS - Filtro de Listagem -----");
        System.out.println("1. Pendentes");
        System.out.println("2. Em Progresso");
        System.out.println("3. Finalizados");
        System.out.println("4. Cancelados");
        System.out.println("0. Todos");

        switch(scanner.nextInt()) {
            case 1:
                this.sweepstakeController.listSweepstakes(Status.PENDING);
            break;
            case 2:
                this.sweepstakeController.listSweepstakes(Status.IN_PROGRESS);
            break;
            case 3:
                this.sweepstakeController.listSweepstakes(Status.FINISHED);
            break;
            case 4:
                this.sweepstakeController.listSweepstakes(Status.CANCELLED);
            break;
            default:
                this.sweepstakeController.listSweepstakes();
            break;
        }
    }
    //#endregion

    //#region FUNCTIONS ITEMS
    private void listItemsPerUser() {
        this.commonController.listItems();;
    }

    private void listItems() {
        System.out.println("----- SMS - Filtro de Listagem -----");
        System.out.println("1. Pendentes");
        System.out.println("2. Autorizados");
        System.out.println("3. Rejeitados");
        System.out.println("0. Todos");

        switch(scanner.nextInt()) {
            case 1:
                this.itemController.listItems(Items.Objects.Item.Status.PENDING);
            break;
            case 2:
                this.itemController.listItems(Items.Objects.Item.Status.AUTHORIZED);
            break;
            case 3:
                this.itemController.listItems(Items.Objects.Item.Status.UNAUTHORIZED);
            break;
            default:
                this.itemController.listItems();
            break;
        }
    }
    //#endregion

    //#region FUNCTIONS MANAGER
    private void analyseSweepstake() {
        Sweepstake spweepstake;

        System.out.println("----- SMS - Analise de Sorteio -----");
        System.out.println("Digite o Id do Sorteio:");
        spweepstake = this.sweepstakeController.findSweepstake(scanner.next());

        if(spweepstake != null) {
            spweepstake.displayInformation();
        } else {
            analyseSweepstake();
        }
        
        System.out.println("Deseja Autorizar (1) ou Rejeitar (2) o Sorteio?");
        switch(scanner.nextInt()) {
            case 1:
                this.managerController.validateSweepstake(spweepstake, true);
            break;
            case 2:
                this.managerController.validateSweepstake(spweepstake, false);
            break;
        }
    }

    private void analyseItem() {
        Item item;

        System.out.println("----- SMS - Analise de Item -----");
        System.out.println("Digite o Id do Sorteio:");
        item = this.itemController.findItem(scanner.next());

        if(item != null) {
            item.displayInformation();
        } else {
            analyseItem();
        }
        
        System.out.println("Deseja Autorizar (1) ou Rejeitar (2) o Sorteio?");
        switch(scanner.nextInt()) {
            case 1:
                this.managerController.validateItem(item, true);
            break;
            case 2:
                this.managerController.validateItem(item, false);
            break;
        }
    }
    //#endregion

    //#region FUNCTIONS COMMON
    private void registerSweepstake() {
        scanner.nextLine();
        System.out.println("----- SMS - Rgistrar Sorteio -----");
        System.out.println("Digite a Resumo:");
        String overview = scanner.nextLine();

        System.out.println("Digite a Descrição:");
        String description = scanner.nextLine();

        System.out.println("Deseja Adicionar Regras(1)?");
        ArrayList<String> rules = new ArrayList<>();
        while(scanner.nextInt() == 1) {
            rules.add(scanner.nextLine());

            System.out.println("Deseja Adicionar uma Nova Regra(1)?");
        }

        Sweepstake sweepstake = new Sweepstake(userController.getSessionUser(), overview, description, rules);

        this.sweepstakeController.registerSweepstake(sweepstake);
        this.commonController.registerSweepstake(sweepstake);
       
    }

    private void registerItem() {
        scanner.nextLine();
        System.out.println("----- SMS - Rgistrar Item -----");
        System.out.println("Digite o Nome:");
        String name = scanner.nextLine();

        System.out.println("Digite a Descrição:");
        String description = scanner.nextLine();

        System.out.println("Digite o Tipo:");
        String type = scanner.nextLine();
        

        Item item = new Item(userController.getSessionUser(), name, description, type);

        this.itemController.registerItem(item);
        this.commonController.registerItem(item);
    }

    private void joinSweepstake() {
        Sweepstake spweepstake;

        System.out.println("----- SMS - Participar de Sorteio -----");
        System.out.println("Digite o Id do Sorteio:");
        spweepstake = sweepstakeController.findSweepstake(scanner.next());

        if(spweepstake != null) {
            spweepstake.displayInformation();
        } else {
            joinSweepstake();
        }

        Response response = sweepstakeController.registerEntrant(spweepstake, userController.getSessionUser());

        if(response == Response.OK) {
            commonController.joinSweepstake(spweepstake);
        }
    }

    private void makeSweepstake() {
        Sweepstake spweepstake;

        System.out.println("----- SMS - Realizar Sorteio -----");
        System.out.println("Digite o Id do Sorteio:");
       
        spweepstake = sweepstakeController.findSweepstake(scanner.next());

        if(spweepstake != null) {
            Response response = commonController.makeSweepstake(spweepstake.getId());
            if(response == Response.OK) {
                spweepstake.displayWinners();
            }
        } else {
            joinSweepstake();
        }
    }
    //#endregion
}
