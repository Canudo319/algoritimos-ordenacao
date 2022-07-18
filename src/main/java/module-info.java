module com.unip.algoritmos_ordenacao {
	requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.unip.algoritmos_ordenacao.util to javafx.fxml;
    exports com.unip.algoritmos_ordenacao.util;
    
    opens com.unip.algoritmos_ordenacao.controllers to javafx.fxml;
    exports com.unip.algoritmos_ordenacao.controllers;
}
