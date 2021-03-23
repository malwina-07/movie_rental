package pl.ampv;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.ampv.movie_cart.model.Copy;
import pl.ampv.movie_cart.model.Genre;
import pl.ampv.movie_cart.model.Movie;
import pl.ampv.movie_cart.repository.CopyRepository;
import pl.ampv.movie_cart.repository.MovieRepository;


import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDataBase implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final CopyRepository copyRepository;

    @Override
    public void run(String... args) throws Exception {
        initAction();
    }

    public void initAction() {
        List<Movie> movies = new LinkedList<>();
        var movie1 = Movie.builder()
                .title("Split")
                .genre(Genre.THRILLER)
                .premiere(LocalDate.of(2016, 1, 1))
                .score("7.0")
                .description("Mężczyzna o mnogiej osobowości porywa trzy nastolatki. Okazuje się, że jedna z jaźni zaczyna dominować nad innymi.").build();
        var movie2 = Movie.builder()
                .title("Knives Out")
                .genre(Genre.CRIME)
                .premiere(LocalDate.of(2019, 1, 1))
                .score("6.0")
                .description("Detektyw Blanc bada sprawę śmierci bogatego pisarza, głowy ekscentrycznej rodziny.").build();
        var movie3 = Movie.builder()
                .title("Joker")
                .genre(Genre.DRAMA)
                .premiere(LocalDate.of(2019, 1, 1))
                .score("6.5")
                .description("Strudzony życiem komik popada w obłęd i staje się psychopatycznym mordercą.").build();
        var movie4 = Movie.builder()
                .title("La La Land")
                .genre(Genre.MUSICAL)
                .premiere(LocalDate.of(2016, 8, 31))
                .score("8.0")
                .description("Los Angeles. Pianista jazzowy zakochuje się w początkującej aktorce.").build();
        var movie5 = Movie.builder()
                .title("Green Book")
                .genre(Genre.COMEDY)
                .premiere(LocalDate.of(2018, 9, 11))
                .score("9.0")
                .description("Drobny cwaniaczek z Bronksu zostaje szoferem ekstrawaganckiego muzyka z wyższych sfer i razem wyruszają na wielotygodniowe tournée.").build();
        var movie6 = Movie.builder()
                .title("Bohemian Rhapsody")
                .genre(Genre.BIOGRAPHICAL)
                .premiere(LocalDate.of(2018, 10, 23))
                .score("9.1")
                .description("Dzięki oryginalnemu brzmieniu Queen staje się jednym z najpopularniejszych zespołów w historii muzyki.").build();
        var movie7 = Movie.builder()
                .title("Fantastic Beasts and Where to Find Them")
                .genre(Genre.FANTASY)
                .premiere(LocalDate.of(2016, 11, 8))
                .score("9.0")
                .description("Nowy Jork jest terroryzowany przez tajemnicze bestie, tymczasem do miasta przyjeżdża pisarz Newt Scamander.").build();
        var movie8 = Movie.builder()
                .title("Doctor Strange")
                .genre(Genre.FANTASY)
                .premiere(LocalDate.of(2016, 10, 13))
                .score("8.3")
                .description("Potężny czarodziej doktor Strange walczy z siłami ciemności, aby ocalić świat.").build();
        var movie9 = Movie.builder()
                .title("Parasite")
                .genre(Genre.DRAMA)
                .premiere(LocalDate.of(2019, 5, 21))
                .score("7.0")
                .description("PKi-woo dostaje pracę jako korepetytor córki zamożnego małżeństwa.").build();
        var movie10 = Movie.builder()
                .title("Avengers: Infinity War")
                .genre(Genre.ACTION)
                .premiere(LocalDate.of(2018, 4, 23))
                .score("6.0")
                .description("Potężny Thanos zbiera Kamienie Nieskończoności w celu narzucenia swojej woli wszystkim istnieniom we wszechświecie.").build();
        var movie11 = Movie.builder()
                .title("Baby Driver")
                .genre(Genre.ACTION)
                .premiere(LocalDate.of(2017, 3, 11))
                .score("7.0")
                .description("Młody chłopak, zmuszany do pracy dla bossa mafii, zostaje wrobiony w napad skazany na niepowodzenie.").build();
        var movie12 = Movie.builder()
                .title("Nocturnal Animals")
                .genre(Genre.THRILLER)
                .premiere(LocalDate.of(2016, 9, 2))
                .score("6.0")
                .description("Właścicielka galerii jest porażona brutalnym thrillerem, w którym odnajduje nawiązania do swojej przeszłości.").build();
        var movie13 = Movie.builder()
                .title("Get Out")
                .genre(Genre.HORROR)
                .premiere(LocalDate.of(2017, 1, 23))
                .score("6.0")
                .description("Czarnoskóry mężczyzna udaje się ze swoją białą partnerką do posiadłości jej rodziców.").build();
        var movie14 = Movie.builder()
                .title("Beauty and the Beast")
                .genre(Genre.MUSICAL)
                .premiere(LocalDate.of(2017, 2, 23))
                .score("7.0")
                .description("Bella gotowa jest zamieszkać w zamku potwora, by ratować ojca. Z czasem między dziewczyną a bestią zaczyna rodzić się uczucie.").build();
        var movie15 = Movie.builder()
                .title("It")
                .genre(Genre.HORROR)
                .premiere(LocalDate.of(2017, 9, 5))
                .score("8.0")
                .description("Dzieci z małego miasteczka terroryzuje tajemnicza istota przybierająca postać klauna.").build();
        var movie16 = Movie.builder()
                .title("Coco")
                .genre(Genre.ANIMATION)
                .premiere(LocalDate.of(2017, 10, 20))
                .score("8.3")
                .description("Dwunastoletni meksykański chłopiec imieniem Miguel usiłuje zgłębić tajemnice rodzinnej legendy.").build();
        var movie17 = Movie.builder()
                .title("Tenet")
                .genre(Genre.SCIENCE_FICTION)
                .premiere(LocalDate.of(2020, 8, 26))
                .score("7.0")
                .description("Uzbrojony tylko w jedno słowo — Tenet — bohater przenika w mroczny świat międzynarodowych szpiegów.").build();
        var movie18 = Movie.builder()
                .title("Black Panther")
                .genre(Genre.ACTION)
                .premiere(LocalDate.of(2018, 1, 29))
                .score("7.8")
                .description("Książę T'Challa przywdziewa kostium Czarnej Pantery po tym, jak jego ojciec zostaje podstępnie zamordowany.").build();
        var movie19 = Movie.builder()
                .title("Dirty Grandpa")
                .genre(Genre.COMEDY)
                .premiere(LocalDate.of(2016, 1, 20))
                .score("6.9")
                .description("Mający niebawem się ożenić Jason zabiera swojego dziadka na przejażdżkę po Florydzie.").build();
        var movie20 = Movie.builder()
                .title("Jojo Rabbit")
                .genre(Genre.DRAMA)
                .premiere(LocalDate.of(2019, 9, 8))
                .score("5.0")
                .description("Młody Niemiec Jojo pomaga Żydówce. Jednocześnie próbuje pozostać nazistą.").build();
        var movie21 = Movie.builder()
                .title("The Conjuring 2")
                .genre(Genre.HORROR)
                .premiere(LocalDate.of(2016, 6, 7))
                .score("5.2")
                .description("Lorraine i Ed Warren udają się do Londynu, aby pomóc samotnej matce, której dom jest nawiedzany przez duchy.").build();
        var movie22 = Movie.builder()
                .title("10 Cloverfield Lane")
                .genre(Genre.SCIENCE_FICTION)
                .premiere(LocalDate.of(2016, 3, 8))
                .score("7.0")
                .description("Po wypadku samochodowym kobieta budzi się w piwnicy mężczyzny, który twierdzi, że uratował ją przed atakiem chemicznym.").build();
        var movie23 = Movie.builder()
                .title("Going in Style")
                .genre(Genre.CRIME)
                .premiere(LocalDate.of(2017, 5, 5))
                .score("7.0")
                .description("Trzej zdesperowani emeryci postanawiają napaść na bank, który pozbawił ich oszczędności.").build();
        var movie24 = Movie.builder()
                .title("Zootopia")
                .genre(Genre.ANIMATION)
                .premiere(LocalDate.of(2016, 2, 10))
                .score("8.1")
                .description("Żyjący z przekrętów lis oraz królik zatrudniony w policji, łączą siły, by rozwiązać kryminalną zagadkę.").build();
        var movie25 = Movie.builder()
                .title("Kong: Skull Island")
                .genre(Genre.FANTASY)
                .premiere(LocalDate.of(2017, 2, 28))
                .score("7.0")
                .description("Grupa odkrywców trafia na wyspę zamieszkałą przez mitycznego Konga.").build();
        var movie26 = Movie.builder()
                .title("Le Mans '66")
                .genre(Genre.BIOGRAPHICAL)
                .premiere(LocalDate.of(2019, 8, 30))
                .score("7.0")
                .description("Projektant Carroll Shelby i kierowca Ken Miles podejmują wyzwanie pokonania samochodów ekipy Ferrari w wyścigu Le Mans.").build();
        var movie27 = Movie.builder()
                .title("Don't Breathe")
                .genre(Genre.THRILLER)
                .premiere(LocalDate.of(2016, 3, 12))
                .score("8.6")
                .description("Kilkoro nastolatków próbuje okraść niewidomego mężczyznę.").build();
        var movie28 = Movie.builder()
                .title("Tomb Raider")
                .genre(Genre.ADVENTURE)
                .premiere(LocalDate.of(2018, 3, 2))
                .score("9.0")
                .description("Lara Croft wyrusza w podróż, by odnaleźć zaginionego ojca na niezbadanej wyspie u wybrzeży Japonii.").build();
        var movie29 = Movie.builder()
                .title("Jumanji: Welcome to the Jungle")
                .genre(Genre.ADVENTURE)
                .premiere(LocalDate.of(2017, 12, 5))
                .score("7.7")
                .description("Czworo przyjaciół odkrywa starą grę, która przenosi ich w świat dżungli.").build();
        var movie30 = Movie.builder()
                .title("Aladdin")
                .genre(Genre.MUSICAL)
                .premiere(LocalDate.of(2019, 5, 8))
                .score("7.3")
                .description("Chłopak o czystym sercu dostaje propozycję od potężnego wezyra, by znalazł dla niego tajemniczą lampę.").build();
        var movie31 = Movie.builder()
                .title("Us")
                .genre(Genre.HORROR)
                .premiere(LocalDate.of(2019, 3, 8))
                .score("6.1")
                .description("Wakacje pewnej rodziny zakłócają nieproszeni goście, którzy nie mają dobrych intencji.").build();
        var movie32 = Movie.builder()
                .title("Lights Out")
                .genre(Genre.HORROR)
                .premiere(LocalDate.of(2016, 3, 8))
                .score("4.3")
                .description("Kobieta jest nawiedzana przez stwora, który pojawia się po zgaszeniu światła.").build();
        var movie33 = Movie.builder()
                .title("A Simple Favor")
                .genre(Genre.COMEDY)
                .premiere(LocalDate.of(2016, 3, 8))
                .score("4.6")
                .description("Kobieta jest nawiedzana przez stwora, który pojawia się po zgaszeniu światła.").build();
        var movie34 = Movie.builder()
                .title("Jungle")
                .genre(Genre.THRILLER)
                .premiere(LocalDate.of(2017, 8, 3))
                .score("8.0")
                .description("Trzech przyjaciół zwiedzających Amerykę Południową postanawia przeżyć przygodę.").build();
        var movie35 = Movie.builder()
                .title("Hunt for the Wilderpeople")
                .genre(Genre.COMEDY)
                .premiere(LocalDate.of(2016, 1, 22))
                .score("5.5")
                .description("Ricky trafia do rodziny zastępczej. Po śmierci przybranej ciotki, chłopak ucieka.").build();
        var movie36 = Movie.builder()
                .title("Titanic")
                .genre(Genre.DRAMA)
                .premiere(LocalDate.of(1997, 12, 19))
                .score("10.0")
                .description("Rok 1912, brytyjski statek Titanic wyrusza w swój dziewiczy rejs do USA. Na pokładzie emigrant Jack przypadkowo spotyka arystokratkę Rose.").build();
        var movie37 = Movie.builder()
                .title("Avengers")
                .genre(Genre.FANTASY)
                .premiere(LocalDate.of(2019, 4, 25))
                .score("10.0")
                .description("Grupa superbohaterów, na czele z Thorem, Iron Manem i Hulkiem, łączy siły, by obronić Ziemię przed inwazją kosmitów.").build();
        var movie38 = Movie.builder()
                .title("Kapitan Marvel")
                .genre(Genre.FANTASY)
                .premiere(LocalDate.of(2019, 3, 8))
                .score("6.0")
                .description("Ziemska kobieta po kontakcie z obcą rasą Kree otrzymuje nadludzkie moce.").build();

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
        movieRepository.save(movie4);
        movieRepository.save(movie5);
        movieRepository.save(movie6);
        movieRepository.save(movie7);
        movieRepository.save(movie8);
        movieRepository.save(movie9);
        movieRepository.save(movie10);
        movieRepository.save(movie11);
        movieRepository.save(movie12);
        movieRepository.save(movie13);
        movieRepository.save(movie14);
        movieRepository.save(movie15);
        movieRepository.save(movie16);
        movieRepository.save(movie17);
        movieRepository.save(movie18);
        movieRepository.save(movie19);
        movieRepository.save(movie20);
        movieRepository.save(movie21);
        movieRepository.save(movie22);
        movieRepository.save(movie23);
        movieRepository.save(movie24);
        movieRepository.save(movie25);
        movieRepository.save(movie26);
        movieRepository.save(movie27);
        movieRepository.save(movie28);
        movieRepository.save(movie29);
        movieRepository.save(movie30);
        movieRepository.save(movie31);
        movieRepository.save(movie32);
        movieRepository.save(movie33);
        movieRepository.save(movie34);
        movieRepository.save(movie35);
        movieRepository.save(movie36);
        movieRepository.save(movie37);
        movieRepository.save(movie38);


        movie1.setCopies(new LinkedList<>());
        movie2.setCopies(new LinkedList<>());
        movie3.setCopies(new LinkedList<>());
        movie4.setCopies(new LinkedList<>());
        movie5.setCopies(new LinkedList<>());
        movie6.setCopies(new LinkedList<>());
        movie7.setCopies(new LinkedList<>());
        movie8.setCopies(new LinkedList<>());
        movie9.setCopies(new LinkedList<>());
        movie10.setCopies(new LinkedList<>());
        movie11.setCopies(new LinkedList<>());
        movie12.setCopies(new LinkedList<>());
        movie13.setCopies(new LinkedList<>());
        movie14.setCopies(new LinkedList<>());
        movie15.setCopies(new LinkedList<>());
        movie16.setCopies(new LinkedList<>());
        movie17.setCopies(new LinkedList<>());
        movie18.setCopies(new LinkedList<>());
        movie19.setCopies(new LinkedList<>());
        movie20.setCopies(new LinkedList<>());
        movie21.setCopies(new LinkedList<>());
        movie22.setCopies(new LinkedList<>());
        movie23.setCopies(new LinkedList<>());
        movie24.setCopies(new LinkedList<>());
        movie25.setCopies(new LinkedList<>());
        movie26.setCopies(new LinkedList<>());
        movie27.setCopies(new LinkedList<>());
        movie28.setCopies(new LinkedList<>());
        movie29.setCopies(new LinkedList<>());
        movie30.setCopies(new LinkedList<>());
        movie31.setCopies(new LinkedList<>());
        movie32.setCopies(new LinkedList<>());
        movie33.setCopies(new LinkedList<>());
        movie34.setCopies(new LinkedList<>());
        movie35.setCopies(new LinkedList<>());
        movie36.setCopies(new LinkedList<>());
        movie37.setCopies(new LinkedList<>());
        movie38.setCopies(new LinkedList<>());

        for (Movie movieElement : movies) {
            Copy copy = Copy.builder().movie(movieElement).build();
            copyRepository.save(copy);
            movieElement.getCopies().add(copy);
            movieRepository.save(movieElement);
        }
    }
}
