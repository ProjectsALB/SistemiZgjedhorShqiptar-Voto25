package ElectionManagementSystem;

import java.util.ArrayList;

public class main {

    // Deklarim i variablave statike
    public static ResultTabulationOverall ResultsTabulation;
    public static ElectionControl electionscontrol;
    public static ArrayList<ElectionNews> electionsnews;
    public static ArrayList<Voter> voters;
    public static ArrayList<Candidate> candidates;
    public static ArrayList<Constituency> constituencys;
    private static Administrator admin;

    public static void main(String[] args) {

        // Per rezultatet
        ResultsTabulation = new ResultTabulationOverall("Jo Filluar", "Jo Filluar", "Jo Mbaruar", "Jo Mbaruar");

        // Kontrolli i zgjedhjeve
        electionscontrol = new ElectionControl(false);

        // Lajme zgjedhore
        electionsnews = new ArrayList<>();
        electionsnews.add(new ElectionNews("I dashur votues! Jepni votën tuaj pa asnjë presion."));

        // Inicializimi i votuesve shqiptarë
        voters = new ArrayList<>();
        voters.add(new Voter("Artan Hoxha",     "AL-001", "Q-TI", "B-TI-1"));
        voters.add(new Voter("Blerina Kuka",     "AL-002", "Q-TI", "B-TI-2"));
        voters.add(new Voter("Dritan Gjoka",     "AL-003", "Q-TI", "B-TI-1"));
        voters.add(new Voter("Elona Mema",       "AL-004", "Q-TI", "B-TI-2"));
        voters.add(new Voter("Fatmir Shehu",     "AL-005", "Q-TI", "B-TI-1"));
        voters.add(new Voter("Genta Leka",       "AL-006", "Q-TI", "B-TI-1"));
        voters.add(new Voter("Ilir Brahimi",     "AL-007", "Q-DR", "B-DR-1"));
        voters.add(new Voter("Jonida Cara",      "AL-008", "Q-DR", "B-DR-1"));
        voters.add(new Voter("Klajdi Basha",     "AL-009", "Q-DR", "B-DR-1"));
        voters.add(new Voter("Lirije Dushku",    "AL-010", "Q-SH", "B-SH-1"));

        // Inicializimi i rretheve zgjedhore
        constituencys = new ArrayList<>();
        constituencys.add(new Constituency("Q-TI", "B-TI-1"));   // Qarku Tirane / Bashkia Tirane 1
        constituencys.add(new Constituency("Q-TI", "B-TI-2"));   // Qarku Tirane / Bashkia Tirane 2
        constituencys.add(new Constituency("Q-DR", "B-DR-1"));   // Qarku Durres / Bashkia Durres 1
        constituencys.add(new Constituency("Q-SH", "B-SH-1"));   // Qarku Shkoder / Bashkia Shkoder 1

        // Inicializimi i kandidatëve me parti shqiptare
        candidates = new ArrayList<>();

        // Qarku Tirane (Q-TI)
        candidates.add(new Candidate("Erion Veliaj",   "1111111111111", "Partia Socialiste e Shqiperise (PS)",               "Q-TI"));
        candidates.add(new Candidate("Sali Berisha",   "2222222222222", "Partia Demokratike e Shqiperise (PD)",              "Q-TI"));
        candidates.add(new Candidate("Ilir Meta",      "3333333333333", "Levizja Socialiste per Integrim (LSI)",             "Q-TI"));
        candidates.add(new Candidate("Vangjel Dule",   "4444444444444", "Partia per Drejtesi, Integrim dhe Unitet (PDIU)",   "Q-TI"));
        candidates.add(new Candidate("Fatmir Mediu",   "5555555555555", "Partia Republikane e Shqiperise (PRS)",             "Q-TI"));

        // Bashkia Tirane 1 (B-TI-1)
        candidates.add(new Candidate("Bledi Cuci",     "6666666666666", "Partia Socialiste e Shqiperise (PS)",               "B-TI-1"));
        candidates.add(new Candidate("Flamur Noka",    "7777777777777", "Partia Demokratike e Shqiperise (PD)",              "B-TI-1"));
        candidates.add(new Candidate("Petrit Vasili",  "8888888888888", "Levizja Socialiste per Integrim (LSI)",             "B-TI-1"));
        candidates.add(new Candidate("Shpetim Idrizi", "9999999999999", "Partia per Drejtesi, Integrim dhe Unitet (PDIU)",   "B-TI-1"));
        candidates.add(new Candidate("Spartak Braho",  "1010101010101", "Partia Republikane e Shqiperise (PRS)",             "B-TI-1"));

        // Bashkia Tirane 2 (B-TI-2)
        candidates.add(new Candidate("Olta Xhacka",   "1111122222333", "Partia Socialiste e Shqiperise (PS)",               "B-TI-2"));
        candidates.add(new Candidate("Arben Ristani",  "2222233333444", "Partia Demokratike e Shqiperise (PD)",              "B-TI-2"));
        candidates.add(new Candidate("Niko Peleshi",   "3333344444555", "Levizja Socialiste per Integrim (LSI)",             "B-TI-2"));
        candidates.add(new Candidate("Muharrem Rraja", "4444455555666", "Partia per Drejtesi, Integrim dhe Unitet (PDIU)",   "B-TI-2"));
        candidates.add(new Candidate("Rexhep Uka",     "5555566666777", "Partia Republikane e Shqiperise (PRS)",             "B-TI-2"));

        // Qarku Durres (Q-DR)
        candidates.add(new Candidate("Damian Gjiknuri","6666677777888", "Partia Socialiste e Shqiperise (PS)",               "Q-DR"));
        candidates.add(new Candidate("Luan Rama",      "7777788888999", "Partia Demokratike e Shqiperise (PD)",              "Q-DR"));
        candidates.add(new Candidate("Agron Shehaj",   "8888899999000", "Levizja Socialiste per Integrim (LSI)",             "Q-DR"));
        candidates.add(new Candidate("Dashamir Shehi", "9999900000111", "Partia per Drejtesi, Integrim dhe Unitet (PDIU)",   "Q-DR"));
        candidates.add(new Candidate("Bashkim Fino",   "0000011111222", "Partia Republikane e Shqiperise (PRS)",             "Q-DR"));

        // Bashkia Durres 1 (B-DR-1)
        candidates.add(new Candidate("Emirjeta Rusi",  "1111100001111", "Partia Socialiste e Shqiperise (PS)",               "B-DR-1"));
        candidates.add(new Candidate("Ardian Kovaci",  "2222200002222", "Partia Demokratike e Shqiperise (PD)",              "B-DR-1"));
        candidates.add(new Candidate("Mimi Kodheli",   "3333300003333", "Levizja Socialiste per Integrim (LSI)",             "B-DR-1"));
        candidates.add(new Candidate("Rezar Tartari",  "4444400004444", "Partia per Drejtesi, Integrim dhe Unitet (PDIU)",   "B-DR-1"));
        candidates.add(new Candidate("Gjin Gjoni",     "5555500005555", "Partia Republikane e Shqiperise (PRS)",             "B-DR-1"));

        // Qarku Shkoder (Q-SH)
        candidates.add(new Candidate("Taulant Balla",  "6666600006666", "Partia Socialiste e Shqiperise (PS)",               "Q-SH"));
        candidates.add(new Candidate("Augustin Goga",  "7777700007777", "Partia Demokratike e Shqiperise (PD)",              "Q-SH"));
        candidates.add(new Candidate("Edmond Spaho",   "8888800008888", "Levizja Socialiste per Integrim (LSI)",             "Q-SH"));
        candidates.add(new Candidate("Mentor Nazarko", "9999900009999", "Partia per Drejtesi, Integrim dhe Unitet (PDIU)",   "Q-SH"));
        candidates.add(new Candidate("Lulzim Basha",   "1212121212121", "Partia Republikane e Shqiperise (PRS)",             "Q-SH"));

        // Bashkia Shkoder 1 (B-SH-1)
        candidates.add(new Candidate("Adriatik Llalla", "1313131313131","Partia Socialiste e Shqiperise (PS)",               "B-SH-1"));
        candidates.add(new Candidate("Pjerin Ndreu",   "1414141414141", "Partia Demokratike e Shqiperise (PD)",              "B-SH-1"));
        candidates.add(new Candidate("Viktor Gumi",    "1515151515151", "Levizja Socialiste per Integrim (LSI)",             "B-SH-1"));
        candidates.add(new Candidate("Gezim Malaj",    "1616161616161", "Partia per Drejtesi, Integrim dhe Unitet (PDIU)",   "B-SH-1"));
        candidates.add(new Candidate("Frok Cupi",      "1717171717171", "Partia Republikane e Shqiperise (PRS)",             "B-SH-1"));

        admin = new Administrator("admin", "admin");

        // Hapja e nderfaqes grafike
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SplashScreen().setVisible(true);
            }
        });
    }

    public static Administrator getAdmin() {
        return admin;
    }
}
