/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ElectionManagementSystem;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lifew
 */
public final class WelcomePage extends javax.swing.JFrame {

    private int indexvoter = 0;
    
    /**
     * Creates new form Login
     */
    public WelcomePage() {
        initComponents();
        LayoutImage();
        setTitle("VOTO-AL 2025");
        time();
        date();
    }
    
    private void BGImageVoterManagment() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/layoutmanagement.jpg"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(imagebg1.getWidth(), imagebg1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        imagebg1.setIcon(scaledIcon);
        initializePAConstituencyComboboxVM();
    }

    public void initializePAConstituencyComboboxVM() {
        // Clear the JComboBox before populating it
        PAConstituencyCombobox.removeAllItems();

        // Populate the JComboBox with the elements from the ArrayList
        for (Constituency constituency : main.constituencys) {
            PAConstituencyCombobox.addItem(constituency.getPA());
        }
        SettingTableDataVM();
    }

    public void SettingTableDataVM() {
        DefaultTableModel model = (DefaultTableModel) VoterDetailsTable.getModel();
        //setting data
        for (Voter voter : main.voters) {
            model.addRow(new Object[]{voter.getName(), voter.getCnic(), voter.getProvincialAssemblyConstituency(), voter.getNationalAssemblyConstituency()});
        }
    }
    
    public void LayoutImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/layout.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(imagelayout.getWidth(), imagelayout.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        imagelayout.setIcon(scaledIcon);
        LogoImageWelcome();
        LogoImageAdmin();
        LogoImageVoter();
        BGImageAdminPanel();
        VoterSideAllFunctions();
        BGImageVoterManagment();
        BGImageCandidateManagment();
        BGImageConstituencyManagement();
        BgimageEC();
        ImageSizeRT();
    }
    
    private void ImageSizeRT() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/ElectionsResultBackground.jpg"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(ResultsBackgroundRT.getWidth(), ResultsBackgroundRT.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        ResultsBackgroundRT.setIcon(scaledIcon);
        tablessizeRT();
    }
    

    private void tablessizeRT() {
        ElectionResultsTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        ElectionResultsTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        ElectionResultsTable.getColumnModel().getColumn(2).setPreferredWidth(290);
        ElectionResultsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        ElectionResultsTable.getColumnModel().getColumn(4).setPreferredWidth(70);

        ElectionResultsOverall.getColumnModel().getColumn(0).setPreferredWidth(60);
        ElectionResultsOverall.getColumnModel().getColumn(1).setPreferredWidth(60);
        ElectionResultsOverall.getColumnModel().getColumn(2).setPreferredWidth(60);
        ElectionResultsOverall.getColumnModel().getColumn(3).setPreferredWidth(100);
        ElectionResultsOverall.getColumnModel().getColumn(4).setPreferredWidth(100);
        ElectionResultsOverall.getColumnModel().getColumn(5).setPreferredWidth(100);
        ElectionResultsOverall.getColumnModel().getColumn(6).setPreferredWidth(100);

        // Set text alignment for ElectionResultsTable
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        ElectionResultsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        ElectionResultsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        ElectionResultsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        ElectionResultsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        ElectionResultsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        ElectionResultsTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        // Set text alignment for ElectionResultsOverall
        ElectionResultsOverall.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        ElectionResultsOverall.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        ElectionResultsOverall.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        ElectionResultsOverall.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        ElectionResultsOverall.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        ElectionResultsOverall.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        ElectionResultsOverall.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        ElectionResultsOverall.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
    }

    private void overallresult() {
        DefaultTableModel model1 = (DefaultTableModel) ElectionResultsOverall.getModel();
        int voterstotal = main.voters.size();
        int votersvoted = 0, notvoted = 0;
        float turnout = 0.0f;

        for (int i = 0; i < voterstotal; i++) {
            if (main.voters.get(i).getHasVoted() == true) {
                votersvoted++;
            }
        }
        main.ResultsTabulation.setVoted(votersvoted);
        notvoted = voterstotal - votersvoted;
        main.ResultsTabulation.setNotVoted(notvoted);
        turnout = (votersvoted * 100) / voterstotal;
        main.ResultsTabulation.setTurnoutOverall(turnout);

        ResultTabulationOverall result2 = main.ResultsTabulation;

        //setting data
        model1.addRow(new Object[]{voterstotal, result2.getVoted(), result2.getNotVoted(), result2.getStartElectionsDate(), result2.getStartElectionsTime(), result2.getEndElectionsDate(), result2.getEndElectionsTime(), result2.getTurnoutOverall() + "%"});
    }
    
    private void BgimageEC() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/WhiteImage.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(bgimge5.getWidth(), bgimge5.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        bgimge5.setIcon(scaledIcon);
        Bgimage1EC();
        startstoragedataEC();
        endstoragedataEC();
    }

    private void Bgimage1EC() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/electionimage.jpg"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(electionimage.getWidth(), electionimage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        electionimage.setIcon(scaledIcon);
    }
    
    public void startstoragedataEC() {
        if (main.electionscontrol.getElectionStartDate() != "" && main.electionscontrol.getElectionStartTime() != "") {
            StartDateLabelEC.setText(main.electionscontrol.getElectionStartDate());
            StartTimeLabelEC.setText(main.electionscontrol.getElectionStartTime());
        }
    }

    public void endstoragedataEC() {
        if (main.electionscontrol.getElectionEndDate() != "" && main.electionscontrol.getElectionEndTime() != "") {
            EndDateLabelEC.setText(main.electionscontrol.getElectionEndDate());
            EndTimeLabelEC.setText(main.electionscontrol.getElectionEndTime());
        }
    }
    
    private void BGImageConstituencyManagement() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/layoutmanagement.jpg"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(imagebg4.getWidth(), imagebg4.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        imagebg4.setIcon(scaledIcon);
        SettingTableDataCoM();
    }
    
    private void SettingTableDataCoM(){ //Constituency Management
        DefaultTableModel model = (DefaultTableModel) AssembliesDetailTable.getModel();
        //setting data
        for (Constituency constituency : main.constituencys) {
            model.addRow(new Object[]{constituency.getPA(), constituency.getNA()});
        }
    }
    
    private void BGImageCandidateManagment() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/layoutmanagement.jpg"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(imagebg2.getWidth(), imagebg2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        imagebg2.setIcon(scaledIcon);
        initializeConstituencyCombobox();
    }

    private void initializeConstituencyCombobox() {
        // Clear the JComboBox before populating it
        ConstituencyCombobox.removeAllItems();

        // Use a HashSet to keep track of unique constituencies
        HashSet<String> uniqueConstituencies = new HashSet<>();

        // Populate the HashSet with the elements from the ArrayList
        for (Constituency constituency : main.constituencys) {
            uniqueConstituencies.add(constituency.getNA());
            uniqueConstituencies.add(constituency.getPA());
        }

        // Add unique items from the HashSet to the JComboBox
        for (String constituency : uniqueConstituencies) {
            ConstituencyCombobox.addItem(constituency);
        }

        // Update the UI (optional, usually Swing does this automatically)
        ConstituencyCombobox.revalidate();
        ConstituencyCombobox.repaint();
        SettingTableDataCM();
    }

    public void SettingTableDataCM() {
        DefaultTableModel model = (DefaultTableModel) CandidateDetailsTable.getModel();
        //setting data
        for (Candidate candidate : main.candidates) {
            model.addRow(new Object[]{candidate.getName(), candidate.getCnic(), candidate.getParty(), candidate.getConstituency()});
        }
    }
    
    private void BGImageAdminPanel() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/Election control.jpeg"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(imagebg.getWidth(), imagebg.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        imagebg.setIcon(scaledIcon);
    }

    public void LogoImageWelcome() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/logo.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(logoimage.getWidth(), logoimage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        logoimage.setIcon(scaledIcon);
    }

    public void LogoImageVoter() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/logo.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(logoimage2.getWidth(), logoimage2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        logoimage2.setIcon(scaledIcon);
    }

    public void LogoImageAdmin() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/logo.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(logoimage1.getWidth(), logoimage1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        logoimage1.setIcon(scaledIcon);
    }

    Timer t;
    SimpleDateFormat st;

    public void time() {
        //getting time
        //time
        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                st = new SimpleDateFormat("hh:mm:ss a");
                String tt = st.format(dt);
                UserTime.setText(tt);
            }
        });
        t.start();
    }

    public void date() {
        //date
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dd = sdf.format(d);
        UserDate.setText(dd);
    }
    
    //Voter Side Voting 
    void VoterSideAllFunctions() {
        BatSymbolImage();
        TigerSymbolImage();
        ArrowImage();
        PairOfScalesImage();
        CraneImage();
        VotingImage();
        verify(indexvoter);
        initilizeCandidate();
        BGImageENU();
        newsdataVoter();
    }
    
    public void newsdataVoter() {
        newstextarea.setEditable(false);
        // Concatenating all news items into a single string
        StringBuilder allNews = new StringBuilder();
         for (ElectionNews news : main.electionsnews) {
            allNews.append(" ---- ").append(news.getNews()).append("\n");
        }
            newstextarea.setText(allNews.toString());
    }
    
    private void BGImageENU() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/electionsnews.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(imagebg3.getWidth(), imagebg3.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        imagebg3.setIcon(scaledIcon);
         SettingTableData();
    }

    public void SettingTableData() {
        DefaultTableModel model = (DefaultTableModel) NewsDetailsTable.getModel();
        //setting data
        for (ElectionNews news : main.electionsnews) {
            model.addRow(new Object[]{news.getNews()});
        }
    }
    
    private void verify(int index) {
        ArrayList<Voter> verifyvoter = main.voters;
        NALabel.setText(verifyvoter.get(index).getNationalAssemblyConstituency());
        PALabel.setText(verifyvoter.get(index).getProvincialAssemblyConstituency());
        indexvoter = index;
        if (main.electionscontrol.isElectionsStatus() == true) {
            NAVoterElectionStatus.setText("STARTED");
            PAVoterElectionStatus.setText("STARTED");
        } else if (main.electionscontrol.isElectionsStatus() == false && main.electionscontrol.getElectionStartDate().equals("")) {
            NAVoterElectionStatus.setText("NOT STARTED");
            PAVoterElectionStatus.setText("NOT STARTED");
        } else {
            NAVoterElectionStatus.setText("ENDED");
            PAVoterElectionStatus.setText("ENDED");
        }
    }
    
    private void initilizeCandidate() {
        for (Candidate candidate : main.candidates) {
            if (NALabel.getText().equals(candidate.getConstituency())) {
                if (candidate.getParty().equals("Partia Socialiste e Shqiperise (PS)")) {
                    NAPTICandidate.setText(candidate.getName());
                } else if (candidate.getParty().equals("Partia Demokratike e Shqiperise (PD)")) {
                    NAPMLNCandidate.setText(candidate.getName());
                } else if (candidate.getParty().equals("Levizja Socialiste per Integrim (LSI)")) {
                    NAPPPCandidate.setText(candidate.getName());
                } else if (candidate.getParty().equals("Partia per Drejtesi, Integrim dhe Unitet (PDIU)")) {
                    NAJIPCandidate.setText(candidate.getName());
                } else if (candidate.getParty().equals("Partia Republikane e Shqiperise (PRS)")) {
                    NATLPCandidate.setText(candidate.getName());
                }
            }
            if (PALabel.getText().equals(candidate.getConstituency())) {
                if (candidate.getParty().equals("Partia Socialiste e Shqiperise (PS)")) {
                    PAPTICandidate.setText(candidate.getName());
                } else if (candidate.getParty().equals("Partia Demokratike e Shqiperise (PD)")) {
                    PAPMLNCandidate.setText(candidate.getName());
                } else if (candidate.getParty().equals("Levizja Socialiste per Integrim (LSI)")) {
                    PAPPPCandidate.setText(candidate.getName());
                } else if (candidate.getParty().equals("Partia per Drejtesi, Integrim dhe Unitet (PDIU)")) {
                    PAJIPCandidate.setText(candidate.getName());
                } else if (candidate.getParty().equals("Partia Republikane e Shqiperise (PRS)")) {
                    PATLPCandidate.setText(candidate.getName());
                }
            }
        }
    }
    
    private void CandidatesVoteCount() {
        for (Candidate candidate : main.candidates) {
            if (NALabel.getText().equals(candidate.getConstituency())) {
                if (candidate.getParty().equals("Partia Socialiste e Shqiperise (PS)") && NAPTI.isSelected()) {
                    candidate.incrementVotes();
                } else if (candidate.getParty().equals("Partia Demokratike e Shqiperise (PD)") && NAPmln.isSelected()) {
                    candidate.incrementVotes();
                } else if (candidate.getParty().equals("Levizja Socialiste per Integrim (LSI)") && NAPPP.isSelected()) {
                    candidate.incrementVotes();
                } else if (candidate.getParty().equals("Partia per Drejtesi, Integrim dhe Unitet (PDIU)") && NAJIP.isSelected()) {
                    candidate.incrementVotes();
                } else if (candidate.getParty().equals("Partia Republikane e Shqiperise (PRS)") && NATLP.isSelected()) {
                    candidate.incrementVotes();
                }
            }
            if (PALabel.getText().equals(candidate.getConstituency())) {
                if (candidate.getParty().equals("Partia Socialiste e Shqiperise (PS)")  && PAPTI.isSelected()) {
                    candidate.incrementVotes();
                } else if (candidate.getParty().equals("Partia Demokratike e Shqiperise (PD)") && PAPmln.isSelected()) {
                    candidate.incrementVotes();
                } else if (candidate.getParty().equals("Levizja Socialiste per Integrim (LSI)") && PAPPP.isSelected()) {
                    candidate.incrementVotes();
                } else if (candidate.getParty().equals("Partia per Drejtesi, Integrim dhe Unitet (PDIU)") && PAJIP.isSelected()) {
                    candidate.incrementVotes();
                } else if (candidate.getParty().equals("Partia Republikane e Shqiperise (PRS)")  && PATLP.isSelected()) {
                    candidate.incrementVotes();
                }
            }
        }
    }
    
    private void BatSymbolImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/PSSymbol.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(NABatSymbol.getWidth(), NABatSymbol.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        NABatSymbol.setIcon(scaledIcon);
  
        imgscale = img.getScaledInstance(PABatSymbol.getWidth(), PABatSymbol.getHeight(), Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgscale);
        PABatSymbol.setIcon(scaledIcon);
    }
    
    private void TigerSymbolImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/PDSymbol.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(NATigerImage.getWidth(), NATigerImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        NATigerImage.setIcon(scaledIcon);
        
        imgscale = img.getScaledInstance(PATigerImage.getWidth(), PATigerImage.getHeight(), Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgscale);
        PATigerImage.setIcon(scaledIcon);
    }
    
    private void ArrowImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/LSISymbol.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(NAArrowSymbol.getWidth(), NAArrowSymbol.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        NAArrowSymbol.setIcon(scaledIcon);
        
        imgscale = img.getScaledInstance(PAArrowSymbol.getWidth(), PAArrowSymbol.getHeight(), Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgscale);
        PAArrowSymbol.setIcon(scaledIcon);
    }
    
    private void PairOfScalesImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/PDIUSymbol.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(NAPairOfScalesSymbol.getWidth(), NAPairOfScalesSymbol.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        NAPairOfScalesSymbol.setIcon(scaledIcon);
        
        imgscale = img.getScaledInstance(PAPairOfScalesSymbol.getWidth(), PAPairOfScalesSymbol.getHeight(), Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgscale);
        PAPairOfScalesSymbol.setIcon(scaledIcon);
    }
    
    private void CraneImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/PRSSymbol.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(NACraneSymbol.getWidth(), NACraneSymbol.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        NACraneSymbol.setIcon(scaledIcon);
        
        imgscale = img.getScaledInstance(PACraneSymbol.getWidth(), PACraneSymbol.getHeight(), Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgscale);
        PACraneSymbol.setIcon(scaledIcon);
    }
    
    private void VotingImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/ElectionManagementSystem/VotingBG.png"));
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(votingbglower.getWidth(), votingbglower.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgscale);
        votingbglower.setIcon(scaledIcon);
        
        imgscale = img.getScaledInstance(votingbgupper.getWidth(), votingbgupper.getHeight(), Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgscale);
        votingbgupper.setIcon(scaledIcon);
        
        imgscale = img.getScaledInstance(votingbglow.getWidth(), votingbglow.getHeight(), Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgscale);
        votingbglow.setIcon(scaledIcon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        NACandidateSelector = new javax.swing.ButtonGroup();
        PACandidateSelector = new javax.swing.ButtonGroup();
        MainPanel = new javax.swing.JTabbedPane();
        WelcomePanel = new javax.swing.JPanel();
        EMSLabel = new javax.swing.JLabel();
        EMSLabel1 = new javax.swing.JLabel();
        AdministratorLoginSelection = new javax.swing.JButton();
        VotersLoginSelection = new javax.swing.JButton();
        AdminMessage = new javax.swing.JLabel();
        AdminWelcome = new javax.swing.JLabel();
        AdminWelcome1 = new javax.swing.JLabel();
        AdminMessage1 = new javax.swing.JLabel();
        logoimage = new javax.swing.JLabel();
        UserTime = new javax.swing.JLabel();
        UserDate = new javax.swing.JLabel();
        imagelayout = new javax.swing.JLabel();
        AdminLoginPanel = new javax.swing.JPanel();
        AdminIDLabel = new javax.swing.JLabel();
        AdminPasswordLabel = new javax.swing.JLabel();
        logoimage1 = new javax.swing.JLabel();
        AdminID = new javax.swing.JTextField();
        AdminPassword = new javax.swing.JPasswordField();
        AdminLogin = new javax.swing.JButton();
        BacktoWelcomeAdmin = new javax.swing.JButton();
        AdministratorLoginLabel = new javax.swing.JLabel();
        AdminWelcome2 = new javax.swing.JLabel();
        AdminMessage2 = new javax.swing.JLabel();
        ckboxadmin = new javax.swing.JCheckBox();
        imagelayout1 = new javax.swing.JLabel();
        VoterLoginPanel = new javax.swing.JPanel();
        VoterVerification1 = new javax.swing.JDesktopPane();
        VoterCNIC = new javax.swing.JTextField();
        VoterCNICLabel = new javax.swing.JLabel();
        VoterLoginLabel = new javax.swing.JLabel();
        VoterNoteMessage = new javax.swing.JLabel();
        VoterMessageCNCI = new javax.swing.JLabel();
        logoimage2 = new javax.swing.JLabel();
        VoterWelcome = new javax.swing.JLabel();
        VoterLogin = new javax.swing.JButton();
        BacktoWelcomeVoter = new javax.swing.JButton();
        VoterMessage1 = new javax.swing.JLabel();
        VoterNote = new javax.swing.JLabel();
        imagelayout2 = new javax.swing.JLabel();
        VoterInterface = new javax.swing.JPanel();
        NABatSymbol = new javax.swing.JLabel();
        NACraneSymbol = new javax.swing.JLabel();
        NAArrowSymbol = new javax.swing.JLabel();
        NAConstituencyLabel = new javax.swing.JLabel();
        NALabel = new javax.swing.JLabel();
        NAElectionStatus = new javax.swing.JLabel();
        NAVoterElectionStatus = new javax.swing.JLabel();
        NATigerImage = new javax.swing.JLabel();
        NAPairOfScalesSymbol = new javax.swing.JLabel();
        NATLPCandidate = new javax.swing.JLabel();
        NAPTICandidate = new javax.swing.JLabel();
        NAPMLNCandidate = new javax.swing.JLabel();
        NAPPPCandidate = new javax.swing.JLabel();
        NAJIPCandidate = new javax.swing.JLabel();
        PAElectionStatus = new javax.swing.JLabel();
        PAVoterElectionStatus = new javax.swing.JLabel();
        PAConstituencyLabel = new javax.swing.JLabel();
        PALabel = new javax.swing.JLabel();
        NAPTI = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        NAPmln = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        NAPPP = new javax.swing.JRadioButton();
        jLabel27 = new javax.swing.JLabel();
        NAJIP = new javax.swing.JRadioButton();
        jLabel28 = new javax.swing.JLabel();
        NATLP = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        PABatSymbol = new javax.swing.JLabel();
        PACraneSymbol = new javax.swing.JLabel();
        PAArrowSymbol = new javax.swing.JLabel();
        PATigerImage = new javax.swing.JLabel();
        PAPairOfScalesSymbol = new javax.swing.JLabel();
        PATLPCandidate = new javax.swing.JLabel();
        PAPTICandidate = new javax.swing.JLabel();
        PAPMLNCandidate = new javax.swing.JLabel();
        PAPPPCandidate = new javax.swing.JLabel();
        PAJIPCandidate = new javax.swing.JLabel();
        PAPTI = new javax.swing.JRadioButton();
        jLabel45 = new javax.swing.JLabel();
        PAPmln = new javax.swing.JRadioButton();
        jLabel46 = new javax.swing.JLabel();
        PAPPP = new javax.swing.JRadioButton();
        jLabel47 = new javax.swing.JLabel();
        PAJIP = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        PATLP = new javax.swing.JRadioButton();
        LogouttoWelcome = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        VoteButton = new javax.swing.JButton();
        NewsButton = new javax.swing.JButton();
        votingbglower = new javax.swing.JLabel();
        votingbgupper = new javax.swing.JLabel();
        votingbglow = new javax.swing.JLabel();
        AdminPanel = new javax.swing.JPanel();
        ResultTabulationButton = new javax.swing.JButton();
        ResultTabulationButton1 = new javax.swing.JButton();
        ConstituencyManagementButton = new javax.swing.JButton();
        VoterManagementButton = new javax.swing.JButton();
        ElectionNewsUpdateButton = new javax.swing.JButton();
        ElectionControlButton = new javax.swing.JButton();
        CandidateManagementButton = new javax.swing.JButton();
        imagebg = new javax.swing.JLabel();
        VoterManagement = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        VoterName = new javax.swing.JTextField();
        VoterCNIC1 = new javax.swing.JTextField();
        UpdateButton = new javax.swing.JButton();
        AddVoterButton = new javax.swing.JButton();
        AddVoterButton1 = new javax.swing.JButton();
        RemoveButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        VoterDetailsTable = new javax.swing.JTable();
        PAConstituencyCombobox = new javax.swing.JComboBox<>();
        NAConstituencyLabel2 = new javax.swing.JLabel();
        imagebg1 = new javax.swing.JLabel();
        CandidateManagement = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        CandidateName = new javax.swing.JTextField();
        CandidateCNIC = new javax.swing.JTextField();
        UpdateButton1 = new javax.swing.JButton();
        AddCandidateButton = new javax.swing.JButton();
        BackCandidateManagement = new javax.swing.JButton();
        RemoveButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        CandidateDetailsTable = new javax.swing.JTable();
        ConstituencyCombobox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        PartyComboBox = new javax.swing.JComboBox<>();
        imagebg2 = new javax.swing.JLabel();
        ElectionNews = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        NewsTextArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        NewsDetailsTable = new javax.swing.JTable();
        UploadNews = new javax.swing.JButton();
        BackAdminPanel = new javax.swing.JButton();
        labelforadministrator = new javax.swing.JLabel();
        imagebg3 = new javax.swing.JLabel();
        ConstuituencyManagement = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        UpdateButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        AssembliesDetailTable = new javax.swing.JTable();
        ProvincialAssemblyCM = new javax.swing.JTextField();
        BackAdminPanelCM = new javax.swing.JButton();
        NationalAssemblyCM = new javax.swing.JTextField();
        addassembly = new javax.swing.JButton();
        removeassembly = new javax.swing.JButton();
        imagebg4 = new javax.swing.JLabel();
        ElectionControl = new javax.swing.JPanel();
        ResetButtonEC = new javax.swing.JButton();
        StartElectionsButtonEC = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        StartDateLabelEC = new javax.swing.JLabel();
        StartTimeLabelEC = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        EndDateLabelEC = new javax.swing.JLabel();
        EndTimeLabelEC = new javax.swing.JLabel();
        BackAdminPanelEC = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        EndElectionsButtonEC = new javax.swing.JButton();
        bgimge5 = new javax.swing.JLabel();
        electionimage = new javax.swing.JLabel();
        ResultsTabulation = new javax.swing.JPanel();
        ElectionsResultsLabelRT = new javax.swing.JLabel();
        jScrollPaneRT = new javax.swing.JScrollPane();
        ElectionResultsTable = new javax.swing.JTable();
        ReportGenerateButtonOverallRT = new javax.swing.JButton();
        ResultTabulationRT = new javax.swing.JButton();
        BackAdminPanelRT = new javax.swing.JButton();
        jScrollPaneRT1 = new javax.swing.JScrollPane();
        ElectionResultsOverall = new javax.swing.JTable();
        ReportGenerateButtonRT = new javax.swing.JButton();
        ResultsBackgroundRT = new javax.swing.JLabel();
        VoterNews = new javax.swing.JPanel();
        VoterWelcome1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        newstextarea = new javax.swing.JTextArea();
        BackAdminPanelVN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(null); // Ndryshimi kryesor këtu

        WelcomePanel.setBackground(new java.awt.Color(255, 255, 255));
        WelcomePanel.setLayout(null);

        EMSLabel.setFont(new java.awt.Font("Stencil", 1, 30)); // NOI18N
        EMSLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EMSLabel.setText("Welkome");
        WelcomePanel.add(EMSLabel);
        EMSLabel.setBounds(100, 40, 610, 40);

        EMSLabel1.setFont(new java.awt.Font("Stencil", 1, 36)); // NOI18N
        EMSLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EMSLabel1.setText("VOTO-AL 2025");
        WelcomePanel.add(EMSLabel1);
        EMSLabel1.setBounds(100, 70, 610, 50);

        AdministratorLoginSelection.setFont(new java.awt.Font("SimSun", 3, 14)); // NOI18N
        AdministratorLoginSelection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.png"))); // NOI18N
        AdministratorLoginSelection.setText("Administrator Login");
        AdministratorLoginSelection.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.gray)));
        AdministratorLoginSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdministratorLoginSelectionActionPerformed(evt);
            }
        });
        WelcomePanel.add(AdministratorLoginSelection);
        AdministratorLoginSelection.setBounds(290, 190, 250, 60);

        VotersLoginSelection.setFont(new java.awt.Font("SimSun", 3, 14)); // NOI18N
        VotersLoginSelection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.png"))); // NOI18N
        VotersLoginSelection.setText("Rregjistrohu si Votues");
        VotersLoginSelection.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.gray)));
        VotersLoginSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VotersLoginSelectionActionPerformed(evt);
            }
        });
        WelcomePanel.add(VotersLoginSelection);
        VotersLoginSelection.setBounds(290, 320, 250, 60);

        AdminMessage.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        AdminMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AdminMessage.setText("If you are an admin,please click on below button to login in to the portal");

        WelcomePanel.add(AdminMessage);
        AdminMessage.setBounds(130, 160, 570, 30);

        AdminWelcome.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        AdminWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AdminWelcome.setText("Administrator:");
        WelcomePanel.add(AdminWelcome);
        AdminWelcome.setBounds(100, 140, 210, 30);

        AdminWelcome1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        AdminWelcome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AdminWelcome1.setText("Votters:");
        WelcomePanel.add(AdminWelcome1);
        AdminWelcome1.setBounds(70, 260, 210, 30);

        AdminMessage1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        AdminMessage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AdminMessage1.setText("If you are a votter,please clcik to the below button,to log in to the portal.");

        WelcomePanel.add(AdminMessage1);
        AdminMessage1.setBounds(80, 280, 570, 30);
        WelcomePanel.add(logoimage);
        logoimage.setBounds(650, 300, 140, 140);

        UserTime.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        UserTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UserTime.setText("Time:");
        WelcomePanel.add(UserTime);
        UserTime.setBounds(680, 10, 110, 17);

        UserDate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        UserDate.setText("Date:");
        WelcomePanel.add(UserDate);
        UserDate.setBounds(30, 10, 130, 17);

        imagelayout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/layout.png"))); // NOI18N
        WelcomePanel.add(imagelayout);
        imagelayout.setBounds(0, 270, 810, 190);

        MainPanel.addTab("Welcome", WelcomePanel);

        AdminLoginPanel.setBackground(new java.awt.Color(255, 255, 255));
        AdminLoginPanel.setLayout(null);

        AdminIDLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        AdminIDLabel.setText("Enetr the ID:");
        AdminLoginPanel.add(AdminIDLabel);
        AdminIDLabel.setBounds(170, 230, 76, 30);

        AdminPasswordLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        AdminPasswordLabel.setText("Password:");
        AdminLoginPanel.add(AdminPasswordLabel);
        AdminPasswordLabel.setBounds(170, 270, 76, 26);

        logoimage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/logo.png"))); // NOI18N
        AdminLoginPanel.add(logoimage1);
        logoimage1.setBounds(380, 70, 90, 90);

        AdminID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        AdminID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AdminLoginPanel.add(AdminID);
        AdminID.setBounds(260, 230, 413, 28);

        AdminPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AdminPassword.setToolTipText("");
        AdminPassword.setActionCommand("null");
        AdminLoginPanel.add(AdminPassword);
        AdminPassword.setBounds(260, 270, 413, 26);


        AdminLogin.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        AdminLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login1.png"))); // NOI18N
        AdminLogin.setText("Log in");
        AdminLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminLoginActionPerformed(evt);
            }
        });
        AdminLoginPanel.add(AdminLogin);
        AdminLogin.setBounds(410, 340, 170, 47);

        BacktoWelcomeAdmin.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        BacktoWelcomeAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.png"))); // NOI18N
        BacktoWelcomeAdmin.setText("BACK");
        BacktoWelcomeAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BacktoWelcomeAdminActionPerformed(evt);
            }
        });
        AdminLoginPanel.add(BacktoWelcomeAdmin);
        BacktoWelcomeAdmin.setBounds(260, 340, 130, 47);

        AdministratorLoginLabel.setFont(new java.awt.Font("Stencil", 1, 30)); // NOI18N
        AdministratorLoginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AdministratorLoginLabel.setText("Register as admin!");
        AdminLoginPanel.add(AdministratorLoginLabel);
        AdministratorLoginLabel.setBounds(110, 30, 610, 56);

        AdminWelcome2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        AdminWelcome2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AdminWelcome2.setText("Welcome Admin! ");
        AdminLoginPanel.add(AdminWelcome2);
        AdminWelcome2.setBounds(320, 170, 219, 21);

        AdminMessage2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        AdminMessage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AdminMessage2.setText("Pklease,log in,to help in managing this platform.");

        AdminLoginPanel.add(AdminMessage2);
        AdminMessage2.setBounds(160, 200, 501, 17);

        ckboxadmin.setBackground(new java.awt.Color(255, 255, 255));
        ckboxadmin.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        ckboxadmin.setText("Show the pass.");

        ckboxadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckboxadminActionPerformed(evt);
            }
        });
        AdminLoginPanel.add(ckboxadmin);
        ckboxadmin.setBounds(550, 300, 120, 19);

        imagelayout1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/layout.png"))); // NOI18N
        imagelayout1.setText("jLabel1");
        AdminLoginPanel.add(imagelayout1);
        imagelayout1.setBounds(0, 140, 920, 340);

        MainPanel.addTab("Register as Admin", AdminLoginPanel);

        VoterLoginPanel.setBackground(new java.awt.Color(255, 255, 255));
        VoterLoginPanel.setLayout(null);
        VoterLoginPanel.add(VoterVerification1);
        VoterVerification1.setBounds(0, 0, 810, 400);

        VoterCNIC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        VoterCNIC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        VoterCNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoterCNICActionPerformed(evt);
            }
        });
        VoterCNIC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VoterCNICKeyPressed(evt);
            }
        });
        VoterLoginPanel.add(VoterCNIC);
        VoterCNIC.setBounds(340, 270, 251, 28);

        VoterCNICLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        VoterCNICLabel.setText("ID Zgjedhore:");
        VoterLoginPanel.add(VoterCNICLabel);
        VoterCNICLabel.setBounds(240, 270, 88, 28);

        VoterLoginLabel.setFont(new java.awt.Font("Stencil", 1, 30)); // NOI18N
        VoterLoginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VoterLoginLabel.setText("Log in as Voter.");
        VoterLoginPanel.add(VoterLoginLabel);
        VoterLoginLabel.setBounds(110, 10, 610, 56);

        VoterNoteMessage.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        VoterNoteMessage.setForeground(new java.awt.Color(45, 181, 0));
        VoterNoteMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VoterNoteMessage.setText("I dashur Votues, ju lutemi verifikoni të dhënat tuaja me përfaqësuesin"
        		+ ""
        		+ " e Komisionit të Zgjedhjeve për të siguruar saktësinë dhe integritetin. "
        		+ "Faleminderit!");
        VoterLoginPanel.add(VoterNoteMessage);
        VoterNoteMessage.setBounds(20, 230, 784, 17);

        VoterMessageCNCI.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        VoterMessageCNCI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VoterMessageCNCI.setText("Shembull: AL-001, AL-010 ...");

        VoterLoginPanel.add(VoterMessageCNCI);
        VoterMessageCNCI.setBounds(410, 300, 190, 15);

        logoimage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/logo.png"))); // NOI18N
        VoterLoginPanel.add(logoimage2);
        logoimage2.setBounds(380, 50, 90, 90);

        VoterWelcome.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        VoterWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VoterWelcome.setText("Welcome Dear Voter!");

        VoterLoginPanel.add(VoterWelcome);
        VoterWelcome.setBounds(300, 150, 219, 21);

        VoterLogin.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        VoterLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login1.png"))); // NOI18N
        VoterLogin.setText("LOGIN");
        VoterLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoterLoginActionPerformed(evt);
            }
        });
        VoterLoginPanel.add(VoterLogin);
        VoterLogin.setBounds(410, 340, 170, 47);

        BacktoWelcomeVoter.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        BacktoWelcomeVoter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.png"))); // NOI18N
        BacktoWelcomeVoter.setText("Back");

        BacktoWelcomeVoter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BacktoWelcomeVoterActionPerformed(evt);
            }
        });
        VoterLoginPanel.add(BacktoWelcomeVoter);
        BacktoWelcomeVoter.setBounds(260, 340, 130, 47);

        VoterMessage1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        VoterMessage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VoterMessage1.setText("Your vote has importance be part of this democratic process");

        VoterLoginPanel.add(VoterMessage1);
        VoterMessage1.setBounds(150, 180, 501, 17);

        VoterNote.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        VoterNote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VoterNote.setText("Note:");
        VoterLoginPanel.add(VoterNote);
        VoterNote.setBounds(380, 210, 84, 17);

        imagelayout2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/layout.png"))); // NOI18N
        imagelayout2.setText("jLabel1");
        VoterLoginPanel.add(imagelayout2);
        imagelayout2.setBounds(0, 140, 920, 340);

        MainPanel.addTab("Login as Voter", VoterLoginPanel);

        VoterInterface.setBackground(new java.awt.Color(255, 255, 255));
        VoterInterface.setLayout(null);

        NABatSymbol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/PSSymbol.png"))); // NOI18N
        NABatSymbol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VoterInterface.add(NABatSymbol);
        NABatSymbol.setBounds(30, 40, 100, 110);

        NACraneSymbol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/PRSSymbol.png"))); // NOI18N
        NACraneSymbol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VoterInterface.add(NACraneSymbol);
        NACraneSymbol.setBounds(680, 40, 100, 110);

        NAArrowSymbol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/LSISymbol.png"))); // NOI18N
        NAArrowSymbol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VoterInterface.add(NAArrowSymbol);
        NAArrowSymbol.setBounds(350, 40, 100, 110);

        NAConstituencyLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        NAConstituencyLabel.setForeground(new java.awt.Color(255, 255, 255));
        NAConstituencyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NAConstituencyLabel.setText("CONSTITUENCY:");
        NAConstituencyLabel.setToolTipText("");
        VoterInterface.add(NAConstituencyLabel);
        NAConstituencyLabel.setBounds(580, 0, 190, 30);

        NALabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        NALabel.setForeground(new java.awt.Color(51, 51, 51));
        NALabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NALabel.setText("sh: Qark");
        VoterInterface.add(NALabel);
        NALabel.setBounds(730, 0, 80, 30);

        NAElectionStatus.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        NAElectionStatus.setForeground(new java.awt.Color(255, 255, 255));
        NAElectionStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NAElectionStatus.setText("ELECTIONS STATUS:");
        NAElectionStatus.setToolTipText("");
        VoterInterface.add(NAElectionStatus);
        NAElectionStatus.setBounds(0, -1, 200, 30);

        NAVoterElectionStatus.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        NAVoterElectionStatus.setForeground(new java.awt.Color(51, 51, 51));
        NAVoterElectionStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NAVoterElectionStatus.setText("The process has finished");
        VoterInterface.add(NAVoterElectionStatus);
        NAVoterElectionStatus.setBounds(190, 0, 160, 30);

        NATigerImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/PDSymbol.png"))); // NOI18N
        NATigerImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VoterInterface.add(NATigerImage);
        NATigerImage.setBounds(190, 40, 100, 110);

        NAPairOfScalesSymbol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/PDIUSymbol.png"))); // NOI18N
        NAPairOfScalesSymbol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VoterInterface.add(NAPairOfScalesSymbol);
        NAPairOfScalesSymbol.setBounds(510, 40, 100, 110);

        NATLPCandidate.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        NATLPCandidate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NATLPCandidate.setText("Do not have registered candidates");
        VoterInterface.add(NATLPCandidate);
        NATLPCandidate.setBounds(660, 160, 140, 17);

        NAPTICandidate.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        NAPTICandidate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NAPTICandidate.setText("Do not have registered candidates");
        VoterInterface.add(NAPTICandidate);
        NAPTICandidate.setBounds(10, 160, 140, 17);

        NAPMLNCandidate.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        NAPMLNCandidate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NAPMLNCandidate.setText("Do not have registered candidates");
        VoterInterface.add(NAPMLNCandidate);
        NAPMLNCandidate.setBounds(170, 160, 140, 17);

        NAPPPCandidate.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        NAPPPCandidate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NAPPPCandidate.setText("Do not have registered candidates");
        VoterInterface.add(NAPPPCandidate);
        NAPPPCandidate.setBounds(330, 160, 140, 17);

        NAJIPCandidate.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        NAJIPCandidate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NAJIPCandidate.setText("Do not have registered candidates");
        VoterInterface.add(NAJIPCandidate);
        NAJIPCandidate.setBounds(490, 160, 140, 20);

        PAElectionStatus.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        PAElectionStatus.setForeground(new java.awt.Color(255, 255, 255));
        PAElectionStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PAElectionStatus.setText("The election status");
        PAElectionStatus.setToolTipText("");
        VoterInterface.add(PAElectionStatus);
        PAElectionStatus.setBounds(0, 220, 200, 19);

        PAVoterElectionStatus.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        PAVoterElectionStatus.setForeground(new java.awt.Color(51, 51, 51));
        PAVoterElectionStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PAVoterElectionStatus.setText("Process ended.");
        VoterInterface.add(PAVoterElectionStatus);
        PAVoterElectionStatus.setBounds(190, 220, 160, 20);

        PAConstituencyLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        PAConstituencyLabel.setForeground(new java.awt.Color(255, 255, 255));
        PAConstituencyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PAConstituencyLabel.setText("CONSTITUENCY:");
        PAConstituencyLabel.setToolTipText("");
        VoterInterface.add(PAConstituencyLabel);
        PAConstituencyLabel.setBounds(580, 220, 190, 20);

        PALabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        PALabel.setForeground(new java.awt.Color(51, 51, 51));
        PALabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PALabel.setText("sh: Bashki");
        VoterInterface.add(PALabel);
        PALabel.setBounds(730, 220, 80, 20);

        NAPTI.setBackground(new java.awt.Color(255, 255, 255));
        NACandidateSelector.add(NAPTI);
        NAPTI.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray)));
        VoterInterface.add(NAPTI);
        NAPTI.setBounds(70, 180, 23, 23);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        VoterInterface.add(jLabel1);
        jLabel1.setBounds(10, 30, 140, 180);

        NAPmln.setBackground(new java.awt.Color(255, 255, 255));
        NACandidateSelector.add(NAPmln);
        NAPmln.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray)));
        VoterInterface.add(NAPmln);
        NAPmln.setBounds(230, 180, 23, 23);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        VoterInterface.add(jLabel2);
        jLabel2.setBounds(170, 30, 140, 180);

        NAPPP.setBackground(new java.awt.Color(255, 255, 255));
        NACandidateSelector.add(NAPPP);
        NAPPP.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray)));
        VoterInterface.add(NAPPP);
        NAPPP.setBounds(390, 180, 23, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        VoterInterface.add(jLabel27);
        jLabel27.setBounds(330, 30, 140, 180);

        NAJIP.setBackground(new java.awt.Color(255, 255, 255));
        NACandidateSelector.add(NAJIP);
        NAJIP.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray)));
        VoterInterface.add(NAJIP);
        NAJIP.setBounds(550, 180, 23, 23);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        VoterInterface.add(jLabel28);
        jLabel28.setBounds(490, 30, 140, 180);

        NATLP.setBackground(new java.awt.Color(255, 255, 255));
        NACandidateSelector.add(NATLP);
        NATLP.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray)));
        VoterInterface.add(NATLP);
        NATLP.setBounds(720, 180, 20, 23);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        VoterInterface.add(jLabel29);
        jLabel29.setBounds(660, 30, 140, 180);

        PABatSymbol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/PSSymbol.png"))); // NOI18N
        PABatSymbol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VoterInterface.add(PABatSymbol);
        PABatSymbol.setBounds(30, 250, 100, 110);

        PACraneSymbol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/PRSSymbol.png"))); // NOI18N
        PACraneSymbol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VoterInterface.add(PACraneSymbol);
        PACraneSymbol.setBounds(680, 250, 100, 110);

        PAArrowSymbol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/LSISymbol.png"))); // NOI18N
        PAArrowSymbol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VoterInterface.add(PAArrowSymbol);
        PAArrowSymbol.setBounds(350, 250, 100, 110);

        PATigerImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/PDSymbol.png"))); // NOI18N
        PATigerImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VoterInterface.add(PATigerImage);
        PATigerImage.setBounds(190, 250, 100, 110);

        PAPairOfScalesSymbol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/PDIUSymbol.png"))); // NOI18N
        PAPairOfScalesSymbol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VoterInterface.add(PAPairOfScalesSymbol);
        PAPairOfScalesSymbol.setBounds(510, 250, 100, 110);

        PATLPCandidate.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        PATLPCandidate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PATLPCandidate.setText("Do not have registered candidates");
        VoterInterface.add(PATLPCandidate);
        PATLPCandidate.setBounds(660, 370, 140, 17);

        PAPTICandidate.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        PAPTICandidate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PAPTICandidate.setText("Do not have registered candidates");
        VoterInterface.add(PAPTICandidate);
        PAPTICandidate.setBounds(10, 370, 140, 17);

        PAPMLNCandidate.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        PAPMLNCandidate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PAPMLNCandidate.setText("Do not have registered candidates");
        VoterInterface.add(PAPMLNCandidate);
        PAPMLNCandidate.setBounds(170, 370, 140, 17);

        PAPPPCandidate.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        PAPPPCandidate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PAPPPCandidate.setText("Do not have registered candidates");
        VoterInterface.add(PAPPPCandidate);
        PAPPPCandidate.setBounds(330, 370, 140, 17);

        PAJIPCandidate.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        PAJIPCandidate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PAJIPCandidate.setText("Do not have registered candidates");
        VoterInterface.add(PAJIPCandidate);
        PAJIPCandidate.setBounds(490, 370, 140, 17);

        PAPTI.setBackground(new java.awt.Color(255, 255, 255));
        PACandidateSelector.add(PAPTI);
        PAPTI.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray)));
        VoterInterface.add(PAPTI);
        PAPTI.setBounds(70, 390, 23, 23);

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        VoterInterface.add(jLabel45);
        jLabel45.setBounds(10, 240, 140, 180);

        PAPmln.setBackground(new java.awt.Color(255, 255, 255));
        PACandidateSelector.add(PAPmln);
        PAPmln.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray)));
        VoterInterface.add(PAPmln);
        PAPmln.setBounds(230, 390, 23, 23);

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        VoterInterface.add(jLabel46);
        jLabel46.setBounds(170, 240, 140, 180);

        PAPPP.setBackground(new java.awt.Color(255, 255, 255));
        PACandidateSelector.add(PAPPP);
        PAPPP.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray)));
        PAPPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PAPPPActionPerformed(evt);
            }
        });
        VoterInterface.add(PAPPP);
        PAPPP.setBounds(390, 390, 23, 23);

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        VoterInterface.add(jLabel47);
        jLabel47.setBounds(330, 240, 140, 180);

        PAJIP.setBackground(new java.awt.Color(255, 255, 255));
        PACandidateSelector.add(PAJIP);
        PAJIP.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray)));
        VoterInterface.add(PAJIP);
        PAJIP.setBounds(550, 390, 23, 23);

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        VoterInterface.add(jLabel3);
        jLabel3.setBounds(0, 420, 960, 4);

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        VoterInterface.add(jLabel48);
        jLabel48.setBounds(490, 240, 140, 180);

        PATLP.setBackground(new java.awt.Color(255, 255, 255));
        PACandidateSelector.add(PATLP);
        PATLP.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray)));
        VoterInterface.add(PATLP);
        PATLP.setBounds(720, 390, 23, 23);

        LogouttoWelcome.setFont(new java.awt.Font("Tw Cen MT", 1, 10)); // NOI18N
        LogouttoWelcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Reset.png"))); // NOI18N
        LogouttoWelcome.setText("Logout");
        LogouttoWelcome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogouttoWelcomeActionPerformed(evt);
            }
        });
        VoterInterface.add(LogouttoWelcome);
        LogouttoWelcome.setBounds(50, 430, 110, 20);

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        VoterInterface.add(jLabel49);
        jLabel49.setBounds(660, 240, 140, 180);

        VoteButton.setFont(new java.awt.Font("Tw Cen MT", 1, 10)); // NOI18N
        VoteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Vote Casting.png"))); // NOI18N
        VoteButton.setText("VOTE");
        VoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoteButtonActionPerformed(evt);
            }
        });
        VoterInterface.add(VoteButton);
        VoteButton.setBounds(620, 430, 100, 20);

        NewsButton.setFont(new java.awt.Font("Arial", 1, 8)); // NOI18N
        NewsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/News Update.png"))); // NOI18N
        NewsButton.setText("Update");
        NewsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        NewsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewsButtonActionPerformed(evt);
            }
        });
        VoterInterface.add(NewsButton);
        NewsButton.setBounds(390, 10, 100, 17);

        votingbglower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/VotingBG.png"))); // NOI18N
        VoterInterface.add(votingbglower);
        votingbglower.setBounds(0, 420, 830, 70);

        votingbgupper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/VotingBG.png"))); // NOI18N
        VoterInterface.add(votingbgupper);
        votingbgupper.setBounds(0, 0, 830, 30);

        votingbglow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/VotingBG.png"))); // NOI18N
        VoterInterface.add(votingbglow);
        votingbglow.setBounds(0, 220, 830, 20);

        MainPanel.addTab("The Voter Sesion", VoterInterface);

        AdminPanel.setBackground(new java.awt.Color(255, 255, 255));
        AdminPanel.setLayout(null);

        ResultTabulationButton.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        ResultTabulationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Result Tabulation.png"))); // NOI18N
        ResultTabulationButton.setText("The Tabulation Result");
        ResultTabulationButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        ResultTabulationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultTabulationButtonActionPerformed(evt);
            }
        });
        AdminPanel.add(ResultTabulationButton);
        ResultTabulationButton.setBounds(420, 250, 230, 62);

        ResultTabulationButton1.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        ResultTabulationButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Reset.png"))); // NOI18N
        ResultTabulationButton1.setText("Logout");
        ResultTabulationButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        ResultTabulationButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultTabulationButton1ActionPerformed(evt);
            }
        });
        AdminPanel.add(ResultTabulationButton1);
        ResultTabulationButton1.setBounds(290, 360, 170, 30);

        ConstituencyManagementButton.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        ConstituencyManagementButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Albania.png"))); // NOI18N
        ConstituencyManagementButton.setText("Electoral Unit Management");

        ConstituencyManagementButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        ConstituencyManagementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConstituencyManagementButtonActionPerformed(evt);
            }
        });
        AdminPanel.add(ConstituencyManagementButton);
        ConstituencyManagementButton.setBounds(430, 160, 230, 62);

        VoterManagementButton.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        VoterManagementButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Voter Management.png"))); // NOI18N
        VoterManagementButton.setText("Voter Unit Management");

        VoterManagementButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        VoterManagementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoterManagementButtonActionPerformed(evt);
            }
        });
        AdminPanel.add(VoterManagementButton);
        VoterManagementButton.setBounds(120, 70, 220, 62);

        ElectionNewsUpdateButton.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        ElectionNewsUpdateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Update Election News.png"))); // NOI18N
        ElectionNewsUpdateButton.setText("The Election News");

        ElectionNewsUpdateButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        ElectionNewsUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElectionNewsUpdateButtonActionPerformed(evt);
            }
        });
        AdminPanel.add(ElectionNewsUpdateButton);
        ElectionNewsUpdateButton.setBounds(120, 160, 220, 62);

        ElectionControlButton.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        ElectionControlButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Elections Control.png"))); // NOI18N
        ElectionControlButton.setText("Electoral Unit Management");

        ElectionControlButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        ElectionControlButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElectionControlButtonActionPerformed(evt);
            }
        });
        AdminPanel.add(ElectionControlButton);
        ElectionControlButton.setBounds(120, 250, 220, 62);

        CandidateManagementButton.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        CandidateManagementButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Candidate Management.png"))); // NOI18N
        CandidateManagementButton.setText("Voter Unit Management");
        CandidateManagementButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        CandidateManagementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CandidateManagementButtonActionPerformed(evt);
            }
        });
        AdminPanel.add(CandidateManagementButton);
        CandidateManagementButton.setBounds(420, 70, 230, 62);

        imagebg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/Election control.jpeg"))); // NOI18N
        AdminPanel.add(imagebg);
        imagebg.setBounds(0, 0, 810, 480);

        MainPanel.addTab("Admin panel", AdminPanel);

        VoterManagement.setBackground(new java.awt.Color(255, 255, 255));
        VoterManagement.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Emri:");
        VoterManagement.add(jLabel5);
        jLabel5.setBounds(190, 30, 160, 30);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID Zgjedhore (auto):");
        VoterManagement.add(jLabel6);
        jLabel6.setBounds(190, 60, 160, 20);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Kuvendi - Qarku:");
        VoterManagement.add(jLabel7);
        jLabel7.setBounds(190, 120, 160, 20);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Bashkia:");
        VoterManagement.add(jLabel4);
        jLabel4.setBounds(190, 90, 160, 30);

        VoterName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        VoterName.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        VoterName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoterNameActionPerformed(evt);
            }
        });
        VoterName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                VoterNameKeyPressed(evt);
            }
        });
        VoterManagement.add(VoterName);
        VoterName.setBounds(350, 30, 270, 25);

        VoterCNIC1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        VoterCNIC1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        VoterCNIC1.setEditable(false);
        VoterCNIC1.setBackground(new java.awt.Color(220, 240, 220));
        VoterCNIC1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        VoterCNIC1.setText(generateNextVoterID());
        VoterManagement.add(VoterCNIC1);
        VoterCNIC1.setBounds(350, 60, 270, 25);

        UpdateButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        UpdateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Update.png"))); // NOI18N
        UpdateButton.setText("Update");

        UpdateButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt);
            }
        });
        VoterManagement.add(UpdateButton);
        UpdateButton.setBounds(590, 160, 120, 30);

        AddVoterButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        AddVoterButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Add.png"))); // NOI18N
       AddVoterButton.setText("Add voters");
        AddVoterButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        AddVoterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddVoterButtonActionPerformed(evt);
            }
        });
        VoterManagement.add(AddVoterButton);
        AddVoterButton.setBounds(280, 160, 110, 30);

        AddVoterButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        AddVoterButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.png"))); // NOI18N
        AddVoterButton1.setText("Back");
        AddVoterButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        AddVoterButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddVoterButton1ActionPerformed(evt);
            }
        });
        VoterManagement.add(AddVoterButton1);
        AddVoterButton1.setBounds(120, 160, 100, 30);

        RemoveButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        RemoveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Remove.png"))); // NOI18N
        RemoveButton.setText("delete voters");
        RemoveButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveButtonActionPerformed(evt);
            }
        });
        VoterManagement.add(RemoveButton);
        RemoveButton.setBounds(440, 160, 110, 30);

        VoterDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "CNIC", "PA", "NA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        VoterDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VoterDetailsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(VoterDetailsTable);

        VoterManagement.add(jScrollPane1);
        jScrollPane1.setBounds(40, 210, 740, 200);

        PAConstituencyCombobox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        PAConstituencyCombobox.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        PAConstituencyCombobox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                PAConstituencyComboboxItemStateChanged(evt);
            }
        });
        PAConstituencyCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PAConstituencyComboboxActionPerformed(evt);
            }
        });
        VoterManagement.add(PAConstituencyCombobox);
        PAConstituencyCombobox.setBounds(350, 90, 270, 29);

        NAConstituencyLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        NAConstituencyLabel2.setForeground(new java.awt.Color(255, 255, 255));
        NAConstituencyLabel2.setText("NA - ");
        VoterManagement.add(NAConstituencyLabel2);
        NAConstituencyLabel2.setBounds(360, 120, 260, 20);

        imagebg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/layoutmanagement.jpg"))); // NOI18N
        VoterManagement.add(imagebg1);
        imagebg1.setBounds(0, 0, 830, 490);

        MainPanel.addTab("VOTO-AL 2025", VoterManagement);

        CandidateManagement.setBackground(new java.awt.Color(255, 255, 255));
        CandidateManagement.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Emri:");
        CandidateManagement.add(jLabel8);
        jLabel8.setBounds(190, 30, 160, 30);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("CNIC:");
        CandidateManagement.add(jLabel9);
        jLabel9.setBounds(190, 60, 160, 20);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Constituency:");
        CandidateManagement.add(jLabel10);
        jLabel10.setBounds(190, 120, 160, 20);

        CandidateName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        CandidateName.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        CandidateName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CandidateNameActionPerformed(evt);
            }
        });
        CandidateName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CandidateNameKeyPressed(evt);
            }
        });
        CandidateManagement.add(CandidateName);
        CandidateName.setBounds(350, 30, 270, 25);

        CandidateCNIC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        CandidateCNIC.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        CandidateCNIC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CandidateCNICKeyPressed(evt);
            }
        });
        CandidateManagement.add(CandidateCNIC);
        CandidateCNIC.setBounds(350, 60, 270, 25);

        UpdateButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        UpdateButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Update.png"))); // NOI18N
        UpdateButton1.setText("Update voters");

        UpdateButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        UpdateButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButton1ActionPerformed(evt);
            }
        });
        CandidateManagement.add(UpdateButton1);
        UpdateButton1.setBounds(590, 170, 120, 30);

        AddCandidateButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        AddCandidateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Add.png"))); // NOI18N
        AddCandidateButton.setText("Add voters");
        AddCandidateButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        AddCandidateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCandidateButtonActionPerformed(evt);
            }
        });
        CandidateManagement.add(AddCandidateButton);
        AddCandidateButton.setBounds(270, 170, 120, 30);

        BackCandidateManagement.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BackCandidateManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.png"))); // NOI18N
        BackCandidateManagement.setText("back");
        BackCandidateManagement.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        BackCandidateManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackCandidateManagementActionPerformed(evt);
            }
        });
        CandidateManagement.add(BackCandidateManagement);
        BackCandidateManagement.setBounds(130, 170, 100, 30);

        RemoveButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        RemoveButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Remove.png"))); // NOI18N
        RemoveButton1.setText("Delete");
        RemoveButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        RemoveButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveButton1ActionPerformed(evt);
            }
        });
        CandidateManagement.add(RemoveButton1);
        RemoveButton1.setBounds(430, 170, 120, 30);

        CandidateDetailsTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        CandidateDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CNIC", "Name", "Party", "Constituency"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CandidateDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CandidateDetailsTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(CandidateDetailsTable);

        CandidateManagement.add(jScrollPane2);
        jScrollPane2.setBounds(20, 220, 790, 220);

        ConstituencyCombobox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ConstituencyCombobox.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        ConstituencyCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConstituencyComboboxActionPerformed(evt);
            }
        });
        CandidateManagement.add(ConstituencyCombobox);
        ConstituencyCombobox.setBounds(350, 120, 270, 29);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Party:");
        CandidateManagement.add(jLabel11);
        jLabel11.setBounds(190, 90, 160, 20);

        PartyComboBox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        PartyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Partia Socialiste e Shqiperise (PS)", "Partia Demokratike e Shqiperise (PD)", "Levizja Socialiste per Integrim (LSI)", "Partia per Drejtesi, Integrim dhe Unitet (PDIU)", "Partia Republikane e Shqiperise (PRS)" }));
        PartyComboBox.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        PartyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PartyComboBoxActionPerformed(evt);
            }
        });
        CandidateManagement.add(PartyComboBox);
        PartyComboBox.setBounds(350, 90, 270, 29);

        imagebg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/layoutmanagement.jpg"))); // NOI18N
        CandidateManagement.add(imagebg2);
        imagebg2.setBounds(0, 0, 830, 490);

        MainPanel.addTab("Candidate Unit Management", CandidateManagement);

        ElectionNews.setBackground(new java.awt.Color(255, 255, 255));
        ElectionNews.setLayout(null);

        jButton6.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Remove.png"))); // NOI18N
        jButton6.setText("delete news");
        jButton6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        ElectionNews.add(jButton6);
        jButton6.setBounds(530, 190, 230, 40);

        NewsTextArea.setColumns(20);
        NewsTextArea.setRows(5);
        jScrollPane3.setViewportView(NewsTextArea);

        ElectionNews.add(jScrollPane3);
        jScrollPane3.setBounds(60, 20, 700, 130);

        NewsDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "News"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(NewsDetailsTable);

        ElectionNews.add(jScrollPane4);
        jScrollPane4.setBounds(40, 260, 740, 130);

        UploadNews.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        UploadNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Upload.png"))); // NOI18N
        UploadNews.setText("update news");
        UploadNews.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        UploadNews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadNewsActionPerformed(evt);
            }
        });
        ElectionNews.add(UploadNews);
        UploadNews.setBounds(260, 190, 230, 40);

        BackAdminPanel.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        BackAdminPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.png"))); // NOI18N
        BackAdminPanel.setText("back");

        BackAdminPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        BackAdminPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackAdminPanelActionPerformed(evt);
            }
        });
        ElectionNews.add(BackAdminPanel);
        BackAdminPanel.setBounds(60, 190, 140, 40);

        labelforadministrator.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        labelforadministrator.setForeground(new java.awt.Color(255, 255, 255));
        labelforadministrator.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelforadministrator.setText("The election news are shown for all the voters.");

        ElectionNews.add(labelforadministrator);
        labelforadministrator.setBounds(510, 150, 240, 17);

        imagebg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/electionsnews.png"))); // NOI18N
        ElectionNews.add(imagebg3);
        imagebg3.setBounds(0, 0, 810, 480);

        MainPanel.addTab("Elections News", ElectionNews);

        ConstuituencyManagement.setBackground(new java.awt.Color(255, 255, 255));
        ConstuituencyManagement.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Bashkia:");
        ConstuituencyManagement.add(jLabel12);
        jLabel12.setBounds(190, 60, 160, 20);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kuvendi - Qarku:");
        ConstuituencyManagement.add(jLabel13);
        jLabel13.setBounds(190, 30, 160, 20);

        UpdateButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        UpdateButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Update.png"))); // NOI18N
        UpdateButton2.setText("UPDATE");
        UpdateButton2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        UpdateButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButton2ActionPerformed(evt);
            }
        });
        ConstuituencyManagement.add(UpdateButton2);
        UpdateButton2.setBounds(600, 100, 120, 30);

        AssembliesDetailTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        AssembliesDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bashkia", "Kuvendi - Qarku"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AssembliesDetailTable.setToolTipText("");
        AssembliesDetailTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AssembliesDetailTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(AssembliesDetailTable);

        ConstuituencyManagement.add(jScrollPane5);
        jScrollPane5.setBounds(100, 150, 640, 200);

        ProvincialAssemblyCM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ProvincialAssemblyCM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        ProvincialAssemblyCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProvincialAssemblyCMActionPerformed(evt);
            }
        });
        ProvincialAssemblyCM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProvincialAssemblyCMKeyPressed(evt);
            }
        });
        ConstuituencyManagement.add(ProvincialAssemblyCM);
        ProvincialAssemblyCM.setBounds(380, 60, 270, 25);

        BackAdminPanelCM.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BackAdminPanelCM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.png"))); // NOI18N
        BackAdminPanelCM.setText("back");
        BackAdminPanelCM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        BackAdminPanelCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackAdminPanelCMActionPerformed(evt);
            }
        });
        ConstuituencyManagement.add(BackAdminPanelCM);
        BackAdminPanelCM.setBounds(120, 100, 90, 30);

        NationalAssemblyCM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        NationalAssemblyCM.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        NationalAssemblyCM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NationalAssemblyCMKeyPressed(evt);
            }
        });
        ConstuituencyManagement.add(NationalAssemblyCM);
        NationalAssemblyCM.setBounds(380, 30, 270, 25);

        addassembly.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        addassembly.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Add.png"))); // NOI18N
        addassembly.setText("Add");
        addassembly.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        addassembly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addassemblyActionPerformed(evt);
            }
        });
        ConstuituencyManagement.add(addassembly);
        addassembly.setBounds(260, 100, 120, 30);

        removeassembly.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        removeassembly.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Remove.png"))); // NOI18N
        removeassembly.setText("Delete");
        removeassembly.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        removeassembly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeassemblyActionPerformed(evt);
            }
        });
        ConstuituencyManagement.add(removeassembly);
        removeassembly.setBounds(430, 100, 120, 30);

        imagebg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/layoutmanagement.jpg"))); // NOI18N
        ConstuituencyManagement.add(imagebg4);
        imagebg4.setBounds(0, 0, 830, 490);

        MainPanel.addTab("Electoral Unit Management", ConstuituencyManagement);

        ElectionControl.setBackground(new java.awt.Color(255, 255, 255));
        ElectionControl.setLayout(null);

        ResetButtonEC.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        ResetButtonEC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Reset.png"))); // NOI18N
        ResetButtonEC.setText("Refresh");

        ResetButtonEC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        ResetButtonEC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonECActionPerformed(evt);
            }
        });
        ElectionControl.add(ResetButtonEC);
        ResetButtonEC.setBounds(330, 20, 170, 50);

        StartElectionsButtonEC.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        StartElectionsButtonEC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Start_1.png"))); // NOI18N
        StartElectionsButtonEC.setText("Start the elections");
        StartElectionsButtonEC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        StartElectionsButtonEC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartElectionsButtonECActionPerformed(evt);
            }
        });
        ElectionControl.add(StartElectionsButtonEC);
        StartElectionsButtonEC.setBounds(160, 120, 230, 62);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("Time:");
        ElectionControl.add(jLabel14);
        jLabel14.setBounds(430, 150, 60, 20);

        StartDateLabelEC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        StartDateLabelEC.setText("the election process has not started yet");
        ElectionControl.add(StartDateLabelEC);
        StartDateLabelEC.setBounds(500, 120, 170, 20);

        StartTimeLabelEC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        StartTimeLabelEC.setText("the election process has not started yet");
        ElectionControl.add(StartTimeLabelEC);
        StartTimeLabelEC.setBounds(500, 150, 170, 20);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Date:");
        ElectionControl.add(jLabel15);
        jLabel15.setBounds(430, 120, 60, 20);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("Time:");
        ElectionControl.add(jLabel16);
        jLabel16.setBounds(420, 290, 60, 20);

        EndDateLabelEC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        EndDateLabelEC.setText("the election process has not started yet");
        ElectionControl.add(EndDateLabelEC);
        EndDateLabelEC.setBounds(490, 260, 170, 20);

        EndTimeLabelEC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        EndTimeLabelEC.setText("the election process has not started yet");
        ElectionControl.add(EndTimeLabelEC);
        EndTimeLabelEC.setBounds(490, 290, 170, 20);

        BackAdminPanelEC.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        BackAdminPanelEC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.png"))); // NOI18N
        BackAdminPanelEC.setText("back");
        BackAdminPanelEC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        BackAdminPanelEC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackAdminPanelECActionPerformed(evt);
            }
        });
        ElectionControl.add(BackAdminPanelEC);
        BackAdminPanelEC.setBounds(360, 380, 110, 30);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Date:");
        ElectionControl.add(jLabel17);
        jLabel17.setBounds(420, 260, 60, 20);

        EndElectionsButtonEC.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        EndElectionsButtonEC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/End.png"))); // NOI18N
        EndElectionsButtonEC.setText("end the process");
        EndElectionsButtonEC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        EndElectionsButtonEC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EndElectionsButtonECActionPerformed(evt);
            }
        });
        ElectionControl.add(EndElectionsButtonEC);
        EndElectionsButtonEC.setBounds(160, 260, 230, 62);

        bgimge5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/WhiteImage.png"))); // NOI18N
        bgimge5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        ElectionControl.add(bgimge5);
        bgimge5.setBounds(410, 100, 240, 240);

        electionimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/electionimage.jpg"))); // NOI18N
        ElectionControl.add(electionimage);
        electionimage.setBounds(0, 0, 810, 480);

        MainPanel.addTab("Elections Control", ElectionControl);

        ResultsTabulation.setBackground(new java.awt.Color(255, 255, 255));
        ResultsTabulation.setLayout(null);

        ElectionsResultsLabelRT.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        ElectionsResultsLabelRT.setForeground(new java.awt.Color(255, 255, 255));
        ElectionsResultsLabelRT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ElectionsResultsLabelRT.setText("election result");
        ResultsTabulation.add(ElectionsResultsLabelRT);
        ElectionsResultsLabelRT.setBounds(300, 0, 250, 40);

        ElectionResultsTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ElectionResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Constituency", "The candidate name", "Party", "Vote", "Tototal of votes", "Outcome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ElectionResultsTable.setRowHeight(40);
        jScrollPaneRT.setViewportView(ElectionResultsTable);

        ResultsTabulation.add(jScrollPaneRT);
        jScrollPaneRT.setBounds(20, 40, 770, 260);

        ReportGenerateButtonOverallRT.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        ReportGenerateButtonOverallRT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Report Card.png"))); // NOI18N
        ReportGenerateButtonOverallRT.setText("The overall report");
        ReportGenerateButtonOverallRT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportGenerateButtonOverallRTActionPerformed(evt);
            }
        });
        ResultsTabulation.add(ReportGenerateButtonOverallRT);
        ReportGenerateButtonOverallRT.setBounds(590, 390, 200, 40);

        ResultTabulationRT.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        ResultTabulationRT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Test Results.png"))); // NOI18N
        ResultTabulationRT.setText("TABULATE RESULTS");
        ResultTabulationRT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultTabulationRTActionPerformed(evt);
            }
        });
        ResultsTabulation.add(ResultTabulationRT);
        ResultTabulationRT.setBounds(140, 390, 220, 40);

        BackAdminPanelRT.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        BackAdminPanelRT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.png"))); // NOI18N
        BackAdminPanelRT.setText("back");
        BackAdminPanelRT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackAdminPanelRTActionPerformed(evt);
            }
        });
        ResultsTabulation.add(BackAdminPanelRT);
        BackAdminPanelRT.setBounds(20, 390, 110, 40);

        jScrollPaneRT1.setColumnHeaderView(null);
        jScrollPaneRT1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        ElectionResultsOverall.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ElectionResultsOverall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                    "Registered", "Voted", "Not Voted", "Start Date", "Start Time", "End Date", "End Time", "Overall Turnout "
                }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ElectionResultsOverall.setRowHeight(50);
        jScrollPaneRT1.setViewportView(ElectionResultsOverall);

        ResultsTabulation.add(jScrollPaneRT1);
        jScrollPaneRT1.setBounds(20, 310, 770, 70);

        ReportGenerateButtonRT.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        ReportGenerateButtonRT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Report Constituency .png"))); // NOI18N
        ReportGenerateButtonRT.setText("ELECTIONS REPORT");
        ReportGenerateButtonRT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportGenerateButtonRTActionPerformed(evt);
            }
        });
        ResultsTabulation.add(ReportGenerateButtonRT);
        ReportGenerateButtonRT.setBounds(370, 390, 210, 40);

        ResultsBackgroundRT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ElectionManagementSystem/ElectionsResultBackground.jpg"))); // NOI18N
        ResultsTabulation.add(ResultsBackgroundRT);
        ResultsBackgroundRT.setBounds(0, 0, 810, 520);

        MainPanel.addTab("Results Tabulation", ResultsTabulation);

        VoterNews.setBackground(new java.awt.Color(255, 255, 255));
        VoterNews.setLayout(null);

        VoterWelcome1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        VoterWelcome1.setForeground(new java.awt.Color(0, 159, 9));
        VoterWelcome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VoterWelcome1.setText("Lates news");
        VoterNews.add(VoterWelcome1);
        VoterWelcome1.setBounds(300, 10, 210, 40);

        newstextarea.setColumns(20);
        newstextarea.setRows(5);
        jScrollPane6.setViewportView(newstextarea);

        VoterNews.add(jScrollPane6);
        jScrollPane6.setBounds(30, 60, 740, 300);

        BackAdminPanelVN.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        BackAdminPanelVN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.png"))); // NOI18N
        BackAdminPanelVN.setText("BACK");
        BackAdminPanelVN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        BackAdminPanelVN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackAdminPanelVNActionPerformed(evt);
            }
        });
        VoterNews.add(BackAdminPanelVN);
        BackAdminPanelVN.setBounds(350, 370, 110, 30);

        MainPanel.addTab("News", VoterNews);

        // Ndryshimi kryesor këtu - zëvendësimi i AbsoluteLayout me setBounds
        MainPanel.setBounds(0, -50, 820, 510);
        getContentPane().add(MainPanel);

        setSize(new java.awt.Dimension(825, 487));
        setLocationRelativeTo(null);
    }

    private void AdministratorLoginSelectionActionPerformed(java.awt.event.ActionEvent evt) {                                                           
        setTitle("VOTO-AL 2025- Admin Login");
        MainPanel.setSelectedIndex(1);
    }                                                          

    private void VotersLoginSelectionActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        VoterVerification1.setVisible(false);
        setTitle("VOTO-AL 2025- Voter Login");
        MainPanel.setSelectedIndex(2);
    }                                                    

    private void AdminLoginActionPerformed(java.awt.event.ActionEvent evt) {                                           
        Administrator admin1 = main.getAdmin();
        String password = new String(AdminPassword.getPassword());
        if (AdminID.getText().equals(admin1.getId()) && password.equals(admin1.getPassword())) {
            JOptionPane.showMessageDialog(this, "Successfully Logged in!!");
            AdminID.setText("");
            AdminPassword.setText("");
            setTitle("Elections Management System - Admin Panel");
            MainPanel.setSelectedIndex(4);
        } else if (AdminID.getText().equals("") || password.equals("")) {
            AdminID.setText("");
            AdminPassword.setText("");
            JOptionPane.showMessageDialog(this, "Enter all credentials!!");
        } else {
            AdminID.setText("");
            AdminPassword.setText("");
            JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
        }
    }                                          

    private void ckboxadminActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if (ckboxadmin.isSelected()) {
            AdminPassword.setEchoChar((char) 0);
        } else {
            AdminPassword.setEchoChar('•');
        }
    }                                          

    private void BacktoWelcomeAdminActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        setTitle("Elections Management System - Welcome Page");
        AdminID.setText("");
        AdminPassword.setText("");
        MainPanel.setSelectedIndex(0);
    }                                                  

    private void VoterCNICActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void VoterCNICKeyPressed(java.awt.event.KeyEvent evt) {
        // Lejon: shkronja (A-Z, a-z), shifra (0-9), vizë (-), Backspace, Delete
        char c = evt.getKeyChar();
        if (Character.isLetterOrDigit(c) || c == '-' ||
            evt.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
            evt.getKeyCode() == KeyEvent.VK_DELETE) {
            VoterCNIC.setEditable(true);
        } else {
            VoterCNIC.setEditable(false);
        }
    }                                    

    private void VoterLoginActionPerformed(java.awt.event.ActionEvent evt) {                                           
        ArrayList<Voter> verifyvoter = main.voters;
        String enteredCnic = VoterCNIC.getText().trim().toUpperCase();

        if (enteredCnic.equals("")) {
            JOptionPane.showMessageDialog(this, "Ju lutem vendosni ID-ne tuaj zgjedhore.");
        } else if (!enteredCnic.matches("AL-\\d{3,}")) {
            JOptionPane.showMessageDialog(this,
                "Formati i gabuar!\nID duhet te jete ne formatin: AL-001, AL-010, AL-011 ...",
                "ID e gabuar", JOptionPane.ERROR_MESSAGE);
            VoterCNIC.setText("");
        } else {
            int voterIndex = -1;
            for (int i = 0; i < verifyvoter.size(); i++) {
                if (enteredCnic.equals(verifyvoter.get(i).getCnic().toUpperCase())) {
                    voterIndex = i;
                    indexvoter = i;
                    break;
                }
            }

            if (voterIndex == -1) {
                JOptionPane.showMessageDialog(this, "Votuesi nuk u gjet! Kontrolloni ID-ne tuaj.");
                VoterCNIC.setText("");
            } else if (main.voters.get(voterIndex).getHasVoted() == true) {
                JOptionPane.showMessageDialog(this,
                    "Ju keni votuar tashme!\nNuk lejohet te votoni dy here.",
                    "Akses i ndaluar", JOptionPane.ERROR_MESSAGE);
                VoterCNIC.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Votues i regjistruar! Miresevini.");
                VoterCNIC.setText("");
                VoterVerification1.removeAll();
                VoterVerify v1 = new VoterVerify(voterIndex, VoterVerification1, BacktoWelcomeVoter, VoterLogin, MainPanel);
                VoterVerification1.add(v1).setVisible(true);
                VoterVerification1.setVisible(true);
                VoterLogin.setVisible(false);
                BacktoWelcomeVoter.setVisible(false);
                verify(indexvoter);
                initilizeCandidate();
                setTitle("VOTO-AL 2025 - Votimi");
            }
        }
    }                                          

    private void BacktoWelcomeVoterActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        setTitle("Elections Management System - Welcome Page");
        VoterCNIC.setText("");
        VoterVerification1.setVisible(false);
        VoterLogin.setVisible(true);
        BacktoWelcomeVoter.setVisible(true);
        MainPanel.setSelectedIndex(0);
    }                                                  

    private void VoteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if (main.electionscontrol.isElectionsStatus() == true) {
            if (main.voters.get(indexvoter).getHasVoted()==false && NACandidateSelector.getSelection() != null && PACandidateSelector.getSelection() != null) {
                CandidatesVoteCount();
                main.voters.get(indexvoter).setHasVoted(true);

                // Shfaq mesazhin e konfirmimit
                JOptionPane.showMessageDialog(this,
                    "JU SAPO VOTUAT!\n\nFaleminderit per pjesemarrjen tuaj!\nVota juaj u regjistrua me sukses.",
                    "Votimi u Krye!", JOptionPane.INFORMATION_MESSAGE);

                // Rivendos nderfaqen dhe kalo te faqja e pare
                VoterVerification1.setVisible(false);
                VoterLogin.setVisible(true);
                BacktoWelcomeVoter.setVisible(true);
                VoterCNIC.setText("");
                NACandidateSelector.clearSelection();
                PACandidateSelector.clearSelection();
                setTitle("VOTO-AL 2025");
                MainPanel.setSelectedIndex(0);

            } else {
                if (NACandidateSelector.getSelection() == null || PACandidateSelector.getSelection() == null) {
                    JOptionPane.showMessageDialog(this, "Zgjidhni kandidatin per te dyja asambletet!");
                } else {
                    JOptionPane.showMessageDialog(this, "Ju keni votuar tashme!", "Votim i bere", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else if (main.electionscontrol.isElectionsStatus() == false && main.electionscontrol.getElectionStartDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Zgjedhjet nuk kane filluar ende!");
        } else {
            JOptionPane.showMessageDialog(this, "Zgjedhjet kane mbaruar!");
        }
    }                                          

    private void NewsButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if (main.electionscontrol.isElectionsStatus() == true) {
            setTitle("Elections Management System - Elections News");
            MainPanel.setSelectedIndex(11);
        } else if (main.electionscontrol.isElectionsStatus() == false && main.electionscontrol.getElectionStartDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Election are not started yet!!");
        } else {
            JOptionPane.showMessageDialog(this, "Election are ended!!");
        }
        newsdataVoter();
    }                                          

    private void PAPPPActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void LogouttoWelcomeActionPerformed(java.awt.event.ActionEvent evt) {                                                
        setTitle("Elections Management System - Welcome Page");
        VoterVerification1.setVisible(false);
        VoterLogin.setVisible(true);
        BacktoWelcomeVoter.setVisible(true);
        MainPanel.setSelectedIndex(2);
    }                                               

    private void ResultTabulationButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        setTitle("Elections Management System - Elections Results Tabulation");
        MainPanel.setSelectedIndex(10);
    }                                                     

    private void ConstituencyManagementButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                                
        ElectionControl validation = main.electionscontrol;
        if (validation.isElectionsStatus()== false) {
            setTitle("Elections Management System - Constituency Management");
            MainPanel.setSelectedIndex(8);
        } else {
            JOptionPane.showMessageDialog(this, "To ensure accuracy, constituency data is temporarily unavailable for editing during elections. Please try again after the election is complete.", "MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    }                                                               

    private void VoterManagementButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        ElectionControl validation = main.electionscontrol;
        if (validation.isElectionsStatus()== false) {
            setTitle("Elections Management System - Voter Management");
            MainPanel.setSelectedIndex(5);
        } else {
            JOptionPane.showMessageDialog(this, "Voters cannot be modified during an election. Please try again after the election is complete.", "MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    }                                                     

    private void ElectionNewsUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                                 
        setTitle("Elections Management System - Elections News");
        MainPanel.setSelectedIndex(7);
    }                                                                

    private void ElectionControlButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        setTitle("Elections Management System - Elections Control");
        MainPanel.setSelectedIndex(9);
    }                                                    

    private void CandidateManagementButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                                 
        ElectionControl validation = main.electionscontrol;
        if (validation.isElectionsStatus()== false) {
            initializeConstituencyCombobox();
            setTitle("Elections Management System - Candidate Management");
            MainPanel.setSelectedIndex(6);
        } else {
            JOptionPane.showMessageDialog(this, "Candidate information cannot be updated during an election to ensure accuracy. Please try again after the election is complete.", "MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    }                                                                

    private void ResultTabulationButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        setTitle("Elections Management System - Welcome Page");
        MainPanel.setSelectedIndex(0);
    }                                                      

    private void VoterNameActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void VoterNameKeyPressed(java.awt.event.KeyEvent evt) {                                     
        char c = evt.getKeyChar();
        if (evt.getExtendedKeyCode() == KeyEvent.VK_DELETE || evt.getKeyCode() == KeyEvent.VK_SHIFT || Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
            VoterName.setEditable(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter only letters!!", "Input Error", JOptionPane.ERROR_MESSAGE);
            VoterName.setEditable(false);
        }
    }                                    

    private void VoterCNIC1KeyPressed(java.awt.event.KeyEvent evt) {                                      
        String Cnic = VoterCNIC1.getText();
        int length = Cnic.length();
        char c = evt.getKeyChar();

        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            if (length < 13) {
                VoterCNIC1.setEditable(true);
            } else {
                VoterCNIC1.setEditable(false);
            }
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                VoterCNIC1.setEditable(true);
            } else {
                VoterCNIC1.setEditable(false);
                JOptionPane.showMessageDialog(null, "Please enter only digits!!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }                                     

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        int i = VoterDetailsTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) VoterDetailsTable.getModel();
        try {
            int voterIndex = -1;
            ArrayList<Voter> verifyvoter = main.voters;
            for (int j = 0; j < verifyvoter.size(); j++) {
                if (VoterCNIC1.getText().equals(verifyvoter.get(j).getCnic())) {
                    if (verifyvoter.get(i).getCnic().equals(verifyvoter.get(j).getCnic())) {
                        continue;
                    }
                    voterIndex = j;
                    break;
                }
            }

            if (voterIndex == -1) {
                model.setValueAt(VoterName.getText(), i, 0);
                model.setValueAt(VoterCNIC1.getText(), i, 1);
                String provincial = PAConstituencyCombobox.getSelectedItem().toString();
                model.setValueAt(provincial, i, 2);
                model.setValueAt(NAConstituencyLabel2.getText(), i, 3);
                JOptionPane.showMessageDialog(this, "Voter Data updated successfully!");
                Voter update = new Voter(VoterName.getText(), VoterCNIC1.getText(), NAConstituencyLabel2.getText(), provincial);
                main.voters.set(i, update);

                VoterName.setText("");
                VoterCNIC1.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Voter's CNIC already exist!", "SIMILARITY", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Select any row you want to update!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }                                           

    private String generateNextVoterID() {
        int max = 0;
        for (Voter v : main.voters) {
            String cnic = v.getCnic();
            if (cnic != null && cnic.startsWith("AL-")) {
                try {
                    int num = Integer.parseInt(cnic.substring(3));
                    if (num > max) max = num;
                } catch (NumberFormatException e) { /* ignore */ }
            }
        }
        return String.format("AL-%03d", max + 1);
    }

    private void AddVoterButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        if (VoterName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Shkruani emrin e votuesit!");
        } else {
            String autoID = VoterCNIC1.getText();
            String provincialassembly = PAConstituencyCombobox.getSelectedItem().toString();
            DefaultTableModel model = (DefaultTableModel) VoterDetailsTable.getModel();
            model.addRow(new Object[]{VoterName.getText(), autoID, provincialassembly, NAConstituencyLabel2.getText()});
            main.voters.add(new Voter(VoterName.getText(), autoID, NAConstituencyLabel2.getText(), provincialassembly));
            JOptionPane.showMessageDialog(this,
                "Votuesi u regjistrua me sukses!\nID e caktuar: " + autoID,
                "Regjistrimi u krye", JOptionPane.INFORMATION_MESSAGE);
            VoterName.setText("");
            // Gjenero ID-në e radhës automatikisht
            VoterCNIC1.setText(generateNextVoterID());
        }
    }                                              

    private void AddVoterButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                                
        setTitle("Elections Management System - Admin Panel");
        MainPanel.setSelectedIndex(4);
    }                                               

    private void RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        int i = VoterDetailsTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) VoterDetailsTable.getModel();
        try {
            model.removeRow(i);
            JOptionPane.showMessageDialog(this, "Voter removed successfully!");
            main.voters.remove(i);
            VoterName.setText("");
            VoterCNIC1.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Select any row you want to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                           

    private void VoterDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {                                               
        int SelectedRow = VoterDetailsTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) VoterDetailsTable.getModel();
        VoterName.setText(model.getValueAt(SelectedRow, 0).toString());
        VoterCNIC1.setText(model.getValueAt(SelectedRow, 1).toString());
    }                                              

    private void PAConstituencyComboboxItemStateChanged(java.awt.event.ItemEvent evt) {                                                        
        String PAarea = PAConstituencyCombobox.getSelectedItem().toString();
        for (int i = 0; i < main.constituencys.size(); i++) {
            if (PAarea == main.constituencys.get(i).getPA()) {
                NAConstituencyLabel2.setText(main.constituencys.get(i).getNA());
            }
        }
    }                                                       

    private void PAConstituencyComboboxActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        // TODO add your handling code here:
    }                                                      

    private void CandidateNameActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void CandidateNameKeyPressed(java.awt.event.KeyEvent evt) {                                         
        char c = evt.getKeyChar();
        if (evt.getExtendedKeyCode() == KeyEvent.VK_DELETE || evt.getKeyCode() == KeyEvent.VK_SHIFT || Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
            CandidateName.setEditable(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter only letters!!", "Input Error", JOptionPane.ERROR_MESSAGE);
            CandidateName.setEditable(false);
        }
    }                                        

    private void CandidateCNICKeyPressed(java.awt.event.KeyEvent evt) {                                         
        String Cnic = CandidateCNIC.getText();
        int length = Cnic.length();
        char c = evt.getKeyChar();

        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            if (length < 13) {
                CandidateCNIC.setEditable(true);
            } else {
                CandidateCNIC.setEditable(false);
            }
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                CandidateCNIC.setEditable(true);
            } else {
                CandidateCNIC.setEditable(false);
                JOptionPane.showMessageDialog(null, "Please enter only digits!!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }                                        

    private void UpdateButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        int i = CandidateDetailsTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) CandidateDetailsTable.getModel();
        try {
            String constituency = ConstituencyCombobox.getSelectedItem().toString();
            String party = PartyComboBox.getSelectedItem().toString();
            int candidateIndex = -1;
            ArrayList<Candidate> verifyCandidate = main.candidates;
            for (int j=0; j < verifyCandidate.size(); j++) {
                if (CandidateCNIC.getText().equals(verifyCandidate.get(j).getCnic())) {
                    if(CandidateCNIC.getText().equals(verifyCandidate.get(j).getCnic())) {
                        break;
                    }
                    candidateIndex = -2;
                    break;
                }
                if (party.equals(verifyCandidate.get(j).getParty()) && verifyCandidate.get(j).getConstituency().equals(constituency)) {
                    candidateIndex = -3;
                    break;
                }
            }
            if (candidateIndex==-1) {
                model.setValueAt(CandidateName.getText(), i, 0);
                model.setValueAt(CandidateCNIC.getText(), i, 1);
                model.setValueAt(party, i, 2);
                model.setValueAt(constituency, i, 3);
                JOptionPane.showMessageDialog(this, "Candidate Data updated successfully!");
                Candidate update = new Candidate(CandidateName.getText(), CandidateCNIC.getText(), party, constituency);
                main.candidates.set(i, update);

                CandidateName.setText("");
                CandidateCNIC.setText("");
            } else if (candidateIndex==-2) {
                JOptionPane.showMessageDialog(this, "Candidate's CNIC already exist!", "SIMILARITY", JOptionPane.ERROR_MESSAGE);
            } else if (candidateIndex==-3) {
                JOptionPane.showMessageDialog(this, "Political Party Candidate already exist in the constituency!", "SIMILARITY", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Select any row you want to update!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }                                            

    private void AddCandidateButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        String constituency = ConstituencyCombobox.getSelectedItem().toString();
        String party = PartyComboBox.getSelectedItem().toString();
        if (CandidateName.getText().equals("") || CandidateCNIC.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter all data!!");
        } else {
            int candidateIndex = -1;
            ArrayList<Candidate> verifyCandidate = main.candidates;
            for (int i=0; i < verifyCandidate.size(); i++) {
                if (CandidateCNIC.getText().equals(verifyCandidate.get(i).getCnic())) {
                    candidateIndex = -2;
                    break;
                }
                if (party.equals(verifyCandidate.get(i).getParty()) && verifyCandidate.get(i).getConstituency().equals(constituency)) {
                    candidateIndex = -3;
                    break;
                }
            }

            if (candidateIndex==-1) {
                DefaultTableModel model = (DefaultTableModel) CandidateDetailsTable.getModel();
                model.addRow(new Object[]{CandidateName.getText(), CandidateCNIC.getText(), party, constituency});
                JOptionPane.showMessageDialog(this, "Candidate added successfully!");
                main.candidates.add(new Candidate(CandidateName.getText(), CandidateCNIC.getText(), party, constituency));
                CandidateName.setText("");
                CandidateCNIC.setText("");
            } else if (candidateIndex==-2) {
                JOptionPane.showMessageDialog(this, "Candidate's CNIC already exist!", "SIMILARITY", JOptionPane.ERROR_MESSAGE);
            } else if (candidateIndex==-3) {
                JOptionPane.showMessageDialog(this, "Political Party Candidate already exist in the constituency!", "SIMILARITY", JOptionPane.ERROR_MESSAGE);
            }
        }
    }                                                  

    private void BackCandidateManagementActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        setTitle("Elections Management System - Admin Panel");
        MainPanel.setSelectedIndex(4);
    }                                                      

    private void RemoveButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        int i = CandidateDetailsTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) CandidateDetailsTable.getModel();
        try {
            model.removeRow(i);
            JOptionPane.showMessageDialog(this, "Candidate removed successfully!");
            main.candidates.remove(i);
            CandidateName.setText("");
            CandidateCNIC.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Select any row you want to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                             

    private void CandidateDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {                                                   
        int SelectedRow = CandidateDetailsTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) CandidateDetailsTable.getModel();
        CandidateName.setText(model.getValueAt(SelectedRow, 0).toString());
        CandidateCNIC.setText(model.getValueAt(SelectedRow, 1).toString());
    }                                                  

    private void ConstituencyComboboxActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }                                                    

    private void PartyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int i = NewsDetailsTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) NewsDetailsTable.getModel();
        try {
            model.removeRow(i);
            JOptionPane.showMessageDialog(this, "News removed successfully!");
            main.electionsnews.remove(i);
            NewsTextArea.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Select any row you want to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                        

    private void UploadNewsActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if (NewsTextArea.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "News field is empty!");
        } else {
            DefaultTableModel model = (DefaultTableModel) NewsDetailsTable.getModel();
            main.electionsnews.add(new ElectionNews(NewsTextArea.getText()));
            model.addRow(new Object[]{NewsTextArea.getText()});
            JOptionPane.showMessageDialog(this, "News Added!");
            NewsTextArea.setText("");
        }
    }                                          

    private void BackAdminPanelActionPerformed(java.awt.event.ActionEvent evt) {                                             
        setTitle("Elections Management System - Admin Panel");
        MainPanel.setSelectedIndex(4);
    }                                            

    private void UpdateButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        int i = AssembliesDetailTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) AssembliesDetailTable.getModel();
        try {
            int constituencyIndex = -1;
            ArrayList<Constituency> verifyConstituency = main.constituencys;
            for (int j=0; j < verifyConstituency.size(); j++) {
                if (ProvincialAssemblyCM.getText().equals(verifyConstituency.get(j).getPA())) {
                    if (verifyConstituency.get(i).getPA().equals(verifyConstituency.get(j).getPA())) {
                        continue;
                    }
                    constituencyIndex = j;
                    break;
                }
            }

            if (constituencyIndex==-1) {
                model.setValueAt(ProvincialAssemblyCM.getText(), i, 0);
                model.setValueAt(NationalAssemblyCM.getText(), i, 1);
                JOptionPane.showMessageDialog(this, "Constituency Data updated successfully!");
                Constituency update = new Constituency(NationalAssemblyCM.getText(), ProvincialAssemblyCM.getText());
                main.constituencys.set(i, update);
                ProvincialAssemblyCM.setText("");
                NationalAssemblyCM.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Bashkia already exist", "SIMILARITY", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Select any row you want to update!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }                                            

    private void AssembliesDetailTableMouseClicked(java.awt.event.MouseEvent evt) {                                                   
        int SelectedRow = AssembliesDetailTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) AssembliesDetailTable.getModel();
        ProvincialAssemblyCM.setText(model.getValueAt(SelectedRow, 0).toString());
        NationalAssemblyCM.setText(model.getValueAt(SelectedRow, 1).toString());
    }                                                  

    private void ProvincialAssemblyCMKeyPressed(java.awt.event.KeyEvent evt) {                                                
        char c = evt.getKeyChar();
        if (evt.getKeyCode() == KeyEvent.VK_SHIFT || Character.isDigit(c) || Character.isUpperCase(c) || c == '-' || evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            ProvincialAssemblyCM.setEditable(true);
        } else {
            ProvincialAssemblyCM.setEditable(false);
            JOptionPane.showMessageDialog(null, "Only capital letters, dash, and digits are allowed!!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                               

    private void NationalAssemblyCMKeyPressed(java.awt.event.KeyEvent evt) {                                              
        char c = evt.getKeyChar();
        if (evt.getKeyCode() == KeyEvent.VK_SHIFT || Character.isDigit(c) || Character.isUpperCase(c) || c == '-' || evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            NationalAssemblyCM.setEditable(true);
        } else {
            NationalAssemblyCM.setEditable(false);
            JOptionPane.showMessageDialog(null, "Only capital letters, dash, and digits are allowed!!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                             

    private void addassemblyActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if (ProvincialAssemblyCM.getText().equals("") || NationalAssemblyCM.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter all data!!");
        } else {
            int constituencyIndex = -1;
            ArrayList<Constituency> verifyConstituency = main.constituencys;
            for (int i=0; i < verifyConstituency.size(); i++) {
                if (ProvincialAssemblyCM.getText().equals(verifyConstituency.get(i).getPA())) {
                    constituencyIndex = i;
                    break;
                }
            }

            if (constituencyIndex==-1) {
                DefaultTableModel model = (DefaultTableModel)AssembliesDetailTable.getModel();
                model.addRow(new Object[]{ProvincialAssemblyCM.getText(), NationalAssemblyCM.getText()});
                JOptionPane.showMessageDialog(this, "Constituency added successfully!");
                main.constituencys.add(new Constituency(NationalAssemblyCM.getText(), ProvincialAssemblyCM.getText()));
                ProvincialAssemblyCM.setText("");
                NationalAssemblyCM.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Bashkia already exist", "SIMILARITY", JOptionPane.ERROR_MESSAGE);
            }
        }
    }                                           

    private void removeassemblyActionPerformed(java.awt.event.ActionEvent evt) {                                              
        int i = AssembliesDetailTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) AssembliesDetailTable.getModel();
        try {
            model.removeRow(i);
            JOptionPane.showMessageDialog(this, "Constituency removed successfully!");
            main.constituencys.remove(i);
            NationalAssemblyCM.setText("");
            ProvincialAssemblyCM.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Select any row you want to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                             

    private void BackAdminPanelCMActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        setTitle("Elections Management System - Admin Panel");
        MainPanel.setSelectedIndex(4);
    }                                                

    private void ResetButtonECActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if (main.electionscontrol.isElectionsStatus() == false) {
            main.electionscontrol = new ElectionControl(false);
            StartDateLabelEC.setText("NOT STARTED YET");
            StartTimeLabelEC.setText("NOT STARTED YET");
            EndDateLabelEC.setText("NOT STARTED YET");
            EndTimeLabelEC.setText("NOT STARTED YET");
            JOptionPane.showMessageDialog(this, "Election start and end date and time have been reset!");

            try {
                String java = System.getProperty("java.home") + "/bin/java";
                ProcessBuilder builder = new ProcessBuilder(java, "-jar", "YourProgram.jar");
                builder.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, "Cannot reset if the election has already started!");
        }
    }                                             

    private void StartElectionsButtonECActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        HashSet<String> uniqueConstituencies = new HashSet<>();
        for (Constituency constituency : main.constituencys) {
            uniqueConstituencies.add(constituency.getNA());
            uniqueConstituencies.add(constituency.getPA());
        }
        int constituencycounts = uniqueConstituencies.size() * 5;
        int candidatecounts = main.candidates.size();

        if (candidatecounts == constituencycounts) {
            if (main.electionscontrol.isElectionsStatus() == false && main.electionscontrol.getElectionStartDate().equals("") && main.electionscontrol.getElectionStartTime().equals("")) {
                main.electionscontrol.setElectionsStatus(true);

                DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime nowdate = LocalDateTime.now();

                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss a");
                LocalDateTime nowtime = LocalDateTime.now();

                main.electionscontrol.setElectionStartDate(dtf1.format(nowdate));
                main.electionscontrol.setElectionStartTime(dtf2.format(nowtime));

                StartDateLabelEC.setText(main.electionscontrol.getElectionStartDate());
                StartTimeLabelEC.setText(main.electionscontrol.getElectionStartTime());
                main.ResultsTabulation.setStartElectionsDate(main.electionscontrol.getElectionStartDate());
                main.ResultsTabulation.setStartElectionsTime(main.electionscontrol.getElectionStartTime());

                // --- Lajme automatike kur nisin zgjedhjet ---
                String startDate = main.electionscontrol.getElectionStartDate();
                String startTime = main.electionscontrol.getElectionStartTime();

                String[] autoNews = {
                    "ZGJEDHJET KANE FILLUAR! Data: " + startDate + " | Ora: " + startTime,
                    "Komisioni Qendror i Zgjedhjeve (KQZ) njofton hapjen zyrtare te procesit zgjedhor.",
                    "Qendrat e votimit ne Tirane, Durres dhe Shkoder jane hapur me sukses.",
                    "Apel per votuesit: Sillni me vete ID-ne tuaj zgjedhore (AL-XXX) per te votuar.",
                    "Procesi zgjedhor do te mbikeqyret nga vezhgues nderkombetar dhe vendas.",
                    "Zgjedhjet mbahen ne " + main.constituencys.size() + " njesi zgjedhore ne te gjithe vendin.",
                    "Te gjithe kandidatet nga 5 partite kryesore jane regjistruar zyrtarisht.",
                    "Votimi eshte i hapur deri ne oren 19:00. Mos e humbisni te drejten tuaj!"
                };

                for (String newsText : autoNews) {
                    main.electionsnews.add(new ElectionNews(newsText));
                }

                // Rifresko tabelën e lajmeve
                DefaultTableModel newsModel = (DefaultTableModel) NewsDetailsTable.getModel();
                newsModel.setRowCount(0);
                for (ElectionNews newsItem : main.electionsnews) {
                    newsModel.addRow(new Object[]{newsItem.getNews()});
                }
                newsdataVoter();

                JOptionPane.showMessageDialog(this,
                    "ZGJEDHJET KANE FILLUAR!\n\nData: " + startDate + "\nOra: " + startTime +
                    "\n\n8 njoftime automatike jane shtuar ne seksionin e lajmeve.",
                    "Zgjedhjet Filluan", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (main.electionscontrol.getElectionStartDate() != "" && main.electionscontrol.getElectionStartTime() != "") {
                    JOptionPane.showMessageDialog(this, "Election is started once", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "All candidates are not assigned to their respective constituencies!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }                                                      

    private void EndElectionsButtonECActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        if (main.electionscontrol.isElectionsStatus() == true && main.electionscontrol.getElectionEndDate().equals("") && main.electionscontrol.getElectionEndTime().equals("")) {
            main.electionscontrol.setElectionsStatus(false);
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime nowdate = LocalDateTime.now();

            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss a");
            LocalDateTime nowtime = LocalDateTime.now();

            main.electionscontrol.setElectionEndDate(dtf1.format(nowdate));
            main.electionscontrol.setElectionEndTime(dtf2.format(nowtime));

            EndDateLabelEC.setText(main.electionscontrol.getElectionEndDate());
            EndTimeLabelEC.setText(main.electionscontrol.getElectionEndTime());
            main.ResultsTabulation.setEndElectionsDate(main.electionscontrol.getElectionEndDate());
            main.ResultsTabulation.setEndElectionsTime(main.electionscontrol.getElectionEndTime());

            JOptionPane.showMessageDialog(this, "ELECTIONS ENDED!");
        } else if (main.electionscontrol.isElectionsStatus() == false && main.electionscontrol.getElectionEndDate().equals("") && main.electionscontrol.getElectionEndTime().equals("")) {
            JOptionPane.showMessageDialog(this, "Elections not started yet!");
        } else {
            JOptionPane.showMessageDialog(this, "Election ended once!");
        }
    }                                                    

    private void BackAdminPanelECActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        setTitle("Elections Management System - Admin Panel");
        MainPanel.setSelectedIndex(4);
    }                                                

    private void ReportGenerateButtonOverallRTActionPerformed(java.awt.event.ActionEvent evt) {                                                                      
        if (ResultTabulationRT.isEnabled() == false) {
            MessageFormat header = new MessageFormat("Election Management System (EMS) Overall Report");
            MessageFormat footer = new MessageFormat("Project by 54689, 54481, 53143, 55241 Page {0}");

            try {
                boolean complete = ElectionResultsOverall.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                if (complete) {
                    JOptionPane.showMessageDialog(this, "Overall report printed successfully!",
                        "Print Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Printing was canceled.",
                        "Print Canceled", JOptionPane.WARNING_MESSAGE);
                }
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(this, "Failed to print report: " + e.getMessage(),
                    "Print Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "The short report generation button is currently disabled.",
                "Button Disabled", JOptionPane.WARNING_MESSAGE);
        }
    }                                                                     

    private void ResultTabulationRTActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        if (main.electionscontrol.isElectionsStatus()==false) {
            overallresult();
            DefaultTableModel model = (DefaultTableModel) ElectionResultsTable.getModel();
            HashMap<String, Integer> highestVotesMap = new HashMap<>();
            HashMap<String, Candidate> winnerMap = new HashMap<>();
            HashMap<String, Integer> voteCountMap = new HashMap<>();

            for (Candidate candidate : main.candidates) {
                String constituency = candidate.getConstituency();
                int votes = candidate.getVotes();

                if (!highestVotesMap.containsKey(constituency) || votes > highestVotesMap.get(constituency)) {
                    highestVotesMap.put(constituency, votes);
                    winnerMap.put(constituency, candidate);
                    voteCountMap.put(constituency, 1);
                } else if (votes == highestVotesMap.get(constituency)) {
                    voteCountMap.put(constituency, voteCountMap.get(constituency) + 1);
                }
            }

            int totalvoters = 0;
            for (Candidate candidate : main.candidates) {
                for (Voter voter1 : main.voters) {
                    if (voter1.getNationalAssemblyConstituency() == candidate.getConstituency()) {
                        totalvoters++;
                    } else if (voter1.getProvincialAssemblyConstituency() == candidate.getConstituency()) {
                        totalvoters++;
                    }
                }
                String outcome = "Loser";
                String constituency = candidate.getConstituency();
                int votes = candidate.getVotes();

                if (votes == highestVotesMap.get(constituency)) {
                    if (voteCountMap.get(constituency) > 1) {
                        if (main.electionscontrol.getElectionEndDate() == "") {
                            outcome = "Pending";
                        } else {
                            outcome = "TIE";
                        }
                    } else if (winnerMap.get(constituency) == candidate) {
                        outcome = "Winner";
                    }
                }

                model.addRow(new Object[]{constituency, candidate.getName(), candidate.getParty(), votes, totalvoters, outcome});
                totalvoters = 0;
            }

            ResultTabulationRT.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "End elections before tabulating results.",
                "Result Tabulation Error", JOptionPane.WARNING_MESSAGE);
        }
    }                                                  

    private void ReportGenerateButtonRTActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        if (ResultTabulationRT.isEnabled()==false) {
            MessageFormat header = new MessageFormat("Election Management System (EMS) Report");
            MessageFormat footer = new MessageFormat("Project by 54689, 54481, 53143, 55241 Page {0}");

            try {
                boolean complete = ElectionResultsTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                if (complete) {
                    JOptionPane.showMessageDialog(null, "Report printed successfully!",
                        "Print Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Printing was canceled.",
                        "Print Canceled", JOptionPane.WARNING_MESSAGE);
                }
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(null, "Failed to print report: " + e.getMessage(),
                    "Print Error", JOptionPane.ERROR_MESSAGE);
            }    
        } else {
            JOptionPane.showMessageDialog(null, " Tabulate Result First ",
                "Print Error", JOptionPane.WARNING_MESSAGE);
        }
    }                                                     

    private void BackAdminPanelRTActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        DefaultTableModel model = (DefaultTableModel) ElectionResultsTable.getModel();      
        model.setRowCount(0);
        ElectionResultsTable.revalidate();
        ElectionResultsTable.repaint();
        
        DefaultTableModel model1 = (DefaultTableModel) ElectionResultsOverall.getModel();      
        model1.setRowCount(0);
        ElectionResultsOverall.revalidate();
        ElectionResultsOverall.repaint();
                
        ResultTabulationRT.setEnabled(true);
        setTitle("Elections Management System - Admin Panel");
        MainPanel.setSelectedIndex(4);
    }                                                

    private void BackAdminPanelVNActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        setTitle("Elections Management System - Vote Casting");
        MainPanel.setSelectedIndex(3);
    }                                                 

    private void ProvincialAssemblyCMActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }                                                    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton AddCandidateButton;
    private javax.swing.JButton AddVoterButton;
    private javax.swing.JButton AddVoterButton1;
    private javax.swing.JTextField AdminID;
    private javax.swing.JLabel AdminIDLabel;
    private javax.swing.JButton AdminLogin;
    private javax.swing.JPanel AdminLoginPanel;
    private javax.swing.JLabel AdminMessage;
    private javax.swing.JLabel AdminMessage1;
    private javax.swing.JLabel AdminMessage2;
    private javax.swing.JPanel AdminPanel;
    private javax.swing.JPasswordField AdminPassword;
    private javax.swing.JLabel AdminPasswordLabel;
    private javax.swing.JLabel AdminWelcome;
    private javax.swing.JLabel AdminWelcome1;
    private javax.swing.JLabel AdminWelcome2;
    private javax.swing.JLabel AdministratorLoginLabel;
    private javax.swing.JButton AdministratorLoginSelection;
    private javax.swing.JTable AssembliesDetailTable;
    private javax.swing.JButton BackAdminPanel;
    private javax.swing.JButton BackAdminPanelCM;
    private javax.swing.JButton BackAdminPanelEC;
    private javax.swing.JButton BackAdminPanelRT;
    private javax.swing.JButton BackAdminPanelVN;
    private javax.swing.JButton BackCandidateManagement;
    private javax.swing.JButton BacktoWelcomeAdmin;
    private javax.swing.JButton BacktoWelcomeVoter;
    private javax.swing.JTextField CandidateCNIC;
    private javax.swing.JTable CandidateDetailsTable;
    private javax.swing.JPanel CandidateManagement;
    private javax.swing.JButton CandidateManagementButton;
    private javax.swing.JTextField CandidateName;
    private javax.swing.JComboBox<String> ConstituencyCombobox;
    private javax.swing.JButton ConstituencyManagementButton;
    private javax.swing.JPanel ConstuituencyManagement;
    private javax.swing.JLabel EMSLabel;
    private javax.swing.JLabel EMSLabel1;
    private javax.swing.JPanel ElectionControl;
    private javax.swing.JButton ElectionControlButton;
    private javax.swing.JPanel ElectionNews;
    private javax.swing.JButton ElectionNewsUpdateButton;
    private javax.swing.JTable ElectionResultsOverall;
    private javax.swing.JTable ElectionResultsTable;
    private javax.swing.JLabel ElectionsResultsLabelRT;
    private javax.swing.JLabel EndDateLabelEC;
    private javax.swing.JButton EndElectionsButtonEC;
    private javax.swing.JLabel EndTimeLabelEC;
    private javax.swing.JButton LogouttoWelcome;
    private javax.swing.JTabbedPane MainPanel;
    private javax.swing.JLabel NAArrowSymbol;
    private javax.swing.JLabel NABatSymbol;
    private javax.swing.ButtonGroup NACandidateSelector;
    private javax.swing.JLabel NAConstituencyLabel;
    private javax.swing.JLabel NAConstituencyLabel2;
    private javax.swing.JLabel NACraneSymbol;
    private javax.swing.JLabel NAElectionStatus;
    private javax.swing.JRadioButton NAJIP;
    private javax.swing.JLabel NAJIPCandidate;
    private javax.swing.JLabel NALabel;
    private javax.swing.JLabel NAPMLNCandidate;
    private javax.swing.JRadioButton NAPPP;
    private javax.swing.JLabel NAPPPCandidate;
    private javax.swing.JRadioButton NAPTI;
    private javax.swing.JLabel NAPTICandidate;
    private javax.swing.JLabel NAPairOfScalesSymbol;
    private javax.swing.JRadioButton NAPmln;
    private javax.swing.JRadioButton NATLP;
    private javax.swing.JLabel NATLPCandidate;
    private javax.swing.JLabel NATigerImage;
    private javax.swing.JLabel NAVoterElectionStatus;
    private javax.swing.JTextField NationalAssemblyCM;
    private javax.swing.JButton NewsButton;
    private javax.swing.JTable NewsDetailsTable;
    private javax.swing.JTextArea NewsTextArea;
    private javax.swing.JLabel PAArrowSymbol;
    private javax.swing.JLabel PABatSymbol;
    private javax.swing.ButtonGroup PACandidateSelector;
    private javax.swing.JComboBox<String> PAConstituencyCombobox;
    private javax.swing.JLabel PAConstituencyLabel;
    private javax.swing.JLabel PACraneSymbol;
    private javax.swing.JLabel PAElectionStatus;
    private javax.swing.JRadioButton PAJIP;
    private javax.swing.JLabel PAJIPCandidate;
    private javax.swing.JLabel PALabel;
    private javax.swing.JLabel PAPMLNCandidate;
    private javax.swing.JRadioButton PAPPP;
    private javax.swing.JLabel PAPPPCandidate;
    private javax.swing.JRadioButton PAPTI;
    private javax.swing.JLabel PAPTICandidate;
    private javax.swing.JLabel PAPairOfScalesSymbol;
    private javax.swing.JRadioButton PAPmln;
    private javax.swing.JRadioButton PATLP;
    private javax.swing.JLabel PATLPCandidate;
    private javax.swing.JLabel PATigerImage;
    private javax.swing.JLabel PAVoterElectionStatus;
    private javax.swing.JComboBox<String> PartyComboBox;
    private javax.swing.JTextField ProvincialAssemblyCM;
    private javax.swing.JButton RemoveButton;
    private javax.swing.JButton RemoveButton1;
    private javax.swing.JButton ReportGenerateButtonOverallRT;
    private javax.swing.JButton ReportGenerateButtonRT;
    private javax.swing.JButton ResetButtonEC;
    private javax.swing.JButton ResultTabulationButton;
    private javax.swing.JButton ResultTabulationButton1;
    private javax.swing.JButton ResultTabulationRT;
    private javax.swing.JLabel ResultsBackgroundRT;
    private javax.swing.JPanel ResultsTabulation;
    private javax.swing.JLabel StartDateLabelEC;
    private javax.swing.JButton StartElectionsButtonEC;
    private javax.swing.JLabel StartTimeLabelEC;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JButton UpdateButton1;
    private javax.swing.JButton UpdateButton2;
    private javax.swing.JButton UploadNews;
    private javax.swing.JLabel UserDate;
    private javax.swing.JLabel UserTime;
    private javax.swing.JButton VoteButton;
    public javax.swing.JTextField VoterCNIC;
    private javax.swing.JTextField VoterCNIC1;
    private javax.swing.JLabel VoterCNICLabel;
    private javax.swing.JTable VoterDetailsTable;
    private javax.swing.JPanel VoterInterface;
    private javax.swing.JButton VoterLogin;
    private javax.swing.JLabel VoterLoginLabel;
    private javax.swing.JPanel VoterLoginPanel;
    private javax.swing.JPanel VoterManagement;
    private javax.swing.JButton VoterManagementButton;
    private javax.swing.JLabel VoterMessage1;
    private javax.swing.JLabel VoterMessageCNCI;
    private javax.swing.JTextField VoterName;
    private javax.swing.JPanel VoterNews;
    private javax.swing.JLabel VoterNote;
    private javax.swing.JLabel VoterNoteMessage;
    private javax.swing.JDesktopPane VoterVerification1;
    private javax.swing.JLabel VoterWelcome;
    private javax.swing.JLabel VoterWelcome1;
    private javax.swing.JButton VotersLoginSelection;
    private javax.swing.JPanel WelcomePanel;
    private javax.swing.JButton addassembly;
    private javax.swing.JLabel bgimge5;
    private javax.swing.JCheckBox ckboxadmin;
    private javax.swing.JLabel electionimage;
    private javax.swing.JLabel imagebg;
    private javax.swing.JLabel imagebg1;
    private javax.swing.JLabel imagebg2;
    private javax.swing.JLabel imagebg3;
    private javax.swing.JLabel imagebg4;
    private javax.swing.JLabel imagelayout;
    private javax.swing.JLabel imagelayout1;
    private javax.swing.JLabel imagelayout2;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPaneRT;
    private javax.swing.JScrollPane jScrollPaneRT1;
    private javax.swing.JLabel labelforadministrator;
    private javax.swing.JLabel logoimage;
    private javax.swing.JLabel logoimage1;
    private javax.swing.JLabel logoimage2;
    private javax.swing.JTextArea newstextarea;
    private javax.swing.JButton removeassembly;
    private javax.swing.JLabel votingbglow;
    private javax.swing.JLabel votingbglower;
    private javax.swing.JLabel votingbgupper;
    // End of variables declaration                   
}    