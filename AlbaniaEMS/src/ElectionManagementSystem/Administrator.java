package ElectionManagementSystem;

/**
 * Klasa Administrator - Trashëgon Person
 * Menaxhon kredencialet e administratorit të sistemit.
 */
public class Administrator extends Person {

    private String username;   // emri i perdoruesit (login ID)
    private String password;   // fjalekalimi

    /**
     * Konstruktori kryesor.
     * @param username  emri i perdoruesit per login
     * @param password  fjalekalimi per login
     */
    public Administrator(String username, String password) {
        super("Administrator", username);   // name="Administrator", cnic=username
        this.username = username;
        this.password = password;
    }

    // ─── Getters ──────────────────────────────────────────────────
    public String getId() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // ─── Setters ──────────────────────────────────────────────────
    public void setId(String username) {
        this.username = username;
        setCnic(username);   // sinkronizo me fushen e trasheguar
    }

    public void setUsername(String username) {
        this.username = username;
        setCnic(username);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ─── toString per debug ───────────────────────────────────────
    @Override
    public String toString() {
        return "Administrator{username='" + username + "'}";
    }
}
