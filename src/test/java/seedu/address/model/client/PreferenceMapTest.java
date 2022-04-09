package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPreferenceMap.SNACK;
import static seedu.address.testutil.TypicalPreferenceMap.SPORTS;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PreferenceMapBuilder;

public class PreferenceMapTest {

    @Test
    public void equals() {
        // same values -> returns true
        PreferenceMap preferenceMapCopy = new PreferenceMapBuilder(SNACK).build();
        assertTrue(SNACK.equals(preferenceMapCopy));

        // same object -> returns true
        assertTrue(SNACK.equals(SNACK));

        // null -> returns false
        assertFalse(SNACK.equals(null));

        // different type -> returns false
        assertFalse(SNACK.equals(5));

        // different preference map -> returns false
        assertFalse(SNACK.equals(SPORTS));

        // same category, different value -> returns false
        PreferenceMap editedPreferenceMap = new PreferenceMapBuilder()
                .withPreference("Sports", "Basketball")
                .build();
        assertFalse(SPORTS.equals(editedPreferenceMap));

        // same value, different category -> returns false
        editedPreferenceMap = new PreferenceMapBuilder()
                .withPreference("Sport", "Basketball")
                .build();
        assertFalse(SPORTS.equals(editedPreferenceMap));

        // different number of preferences -> returns false
        editedPreferenceMap = new PreferenceMapBuilder()
                .withPreference("Drink", "Beer")
                .withPreference("Sports", "Tennis")
                .build();
        assertFalse(SPORTS.equals(editedPreferenceMap));
    }
}
