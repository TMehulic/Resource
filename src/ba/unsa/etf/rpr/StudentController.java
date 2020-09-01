package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class StudentController {

    private ObservableList<Course> courses;
    @FXML
    private ListView<Course> listView;

    @FXML
    private Circle circle;


    public StudentController() {

        courses= FXCollections.observableArrayList();

        courses.addAll(
                new Course("Numerički algoritmi","Cilj ovog kursa je upoznavanje sa problemima vezanim za implementaciju računanja sa realnim brojevima na računaru, te dizajn brzih, tačnih i pouzdanih algoritama za rješavanje tipičnih problema numeričke prirode (računanja sa matricama, rješavanje jednačina i sistema jednačina, interpolacija, aproksimacija, numeričko diferenciranje i integriranje, numeričko rješavanje diferencijalnih jednačina, brza Fourierova transformacija).",5),
                new Course("Algoritmi i strukure podataka", "Cilj kursa je sticanja koherentnog znanja o tehnikama za implementiranje algoritama i strukturama podataka. U isto vrijeme kurs pruža studentima mogućnost da unaprijede svoje programersko znanje prilikom razvoja i primjene raznih algoritama u okviru konkretnih programskih rješenja.",5),
                new Course("Diskretna matematika","\n" +
                        "Cilj kursa je da obezbijedi studentima solidne teorijske osnove kako bi na sistematičan način mogli rješavati matematske probleme informatičkog karaktera, a koji su vezani za elementarnu teoriju brojeva, kombinatoriku, elementarnu teoriju vjerovatnoće, teoriju informacija, teoriju grafova i teoriju diskretnih sistema.",5),
                new Course("Logički dizajn","\n" +
                        "Svrha ovog predmeta je uvođenje studenta u principe logičkog dizajna i projektovanja osnovnih komponenti digitalnog računara tradicionalnim i savremenim metodama. U hijerarhiji apstrakcija počinje od logičkih kola i završava sa jednostavnim ali funkcionalnim mikroprogramiranim procesorom (centralnom procesnom jedinicom) i njegovim mašinskim jezikom. Studenti treba da razumiju principe na kojima rade jednostavni računarski sistemi, kao i prednosti i nedostatke hardverskog i softverskog rješavanja problema. Preduslovi za razumjevanje gradiva ovog kursa su poznavanje osnova digitalne (prekidačke) elektronike kao i osnova računarstva. Ovaj kurs predstavlja preduslov za razumjevanje računarskih arhitektura.",5)
        );

    }

    @FXML
    public void initialize(){
        listView.setItems(courses);
        listView.setCellFactory(testnaListView -> new CourseListCell());
        Image im = new Image("/images/test.png");
        circle.setFill(new ImagePattern(im));
    }
}
