package hw.spring.model;

import hw.spring.model.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by kamil on 12.05.17.
 */
@Entity
public class UserActivity {

    @Id @GeneratedValue int id;

    @ManyToOne
    private User theUser;

    @ManyToOne
    private Activity activity;

    private boolean present;

    public User getTheUser() {
        return theUser;
    }

    public void setTheUser(User theUser) {
        this.theUser = theUser;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
