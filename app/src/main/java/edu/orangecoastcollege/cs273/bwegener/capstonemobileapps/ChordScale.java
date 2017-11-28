package edu.orangecoastcollege.cs273.bwegener.capstonemobileapps;

import java.util.ArrayList;

/**
 * Subclass of <code>SoundObject</code> used to instantiate a <code>ChordScale</code> object.
 *
 * <code>ChordScale</code> objects are essentially an array of <code>Note</code> objects referred to
 * as "chord members" in this context.
 *
 * <code>ChordScale</code> objects can be used to represent CHORDS (two or more mutually-exclusive
 * <code>Note</code>s played simultaneously) and SCALES (a linear sequence of <code>Note</code>s
 * played in ascending or descending order.
 *
 * A <code>ChordScale</code> can be instantiated alone or as a part of a <code>ChordSequence</code>
 *
 * @author Ryan Millett
 * @version 2.0
 */
public class ChordScale extends SoundObject {

    /**
     * String constant used to indicate that all chord members contained in a <code>ChordScale</code>
     * are to be played simultaneous as a "block chord".
     */
    public static final String PLAYBACK_MODE_BLOCK_CHORD = "Block";

    /**
     * String constant used to indicate that chord members contained in a <code>ChordScale</code> are
     * to be played as an upward arpeggio--i.e. in sequence from lowest pitch to highest.
     */
    public static final String PLAYBACK_MODE_ARP_UP = "ArpUp";

    /**
     * String constant used to indicate that chord members contained in a <code>ChordScale</code> are
     * to be played as a downward arpeggio--i.e. in sequence from highest pitch to lowest.
     */
    public static final String PLAYBACK_MODE_ARP_DOWN = "ArpDown";

    /**
     * String constant used to indicate that chord members contained in a <code>ChordScale</code> are
     * to be played in "Alberti Bass" fashion--i.e. in the order lowest, highest, middle, highest.
     *
     * Important Note: can only be applied to a <code>ChordScale</code> containing ONLY three (3)
     * chord members.
     */
    public static final String PLAYBACK_MODE_ALBERTI_BASS = "Alberti";

    private long mId;
    private int mSize;
    private ArrayList<Note> mChordMembers;
    private String mPlayBackMode;
    private int mDurationMilliseconds;

    /**
     * Default constructor
     */
    public ChordScale() {
        super();
        mId = -1;
        mSize = 0;
        mChordMembers = new ArrayList<>(mSize);
        mPlayBackMode = PLAYBACK_MODE_BLOCK_CHORD;
        mDurationMilliseconds = SoundObject.DEFAULT_DURATION_MILLISECONDS;
    }

    public ChordScale(String name) {
        super(name);
        mId = -1;
        mSize = 0;
        mChordMembers = new ArrayList<>(mSize);
        mPlayBackMode = PLAYBACK_MODE_BLOCK_CHORD;
        mDurationMilliseconds = SoundObject.DEFAULT_DURATION_MILLISECONDS;
    }

    public ChordScale(String name, long id) {
        super(name);
        mId = id;
        mSize = 0;
        mChordMembers = new ArrayList<>(mSize);
        mPlayBackMode = PLAYBACK_MODE_BLOCK_CHORD;
        mDurationMilliseconds = SoundObject.DEFAULT_DURATION_MILLISECONDS;
    }

    /**
     * Gets the size of the <code>ChordScale</code> object--i.e. how many individual <code>Note</code>
     * objects are contained in the <code>ChordScale</code>.
     *
     * @return int value representing the number of individual <code>Note</code> objects contained in
     *          the <code>ChordSequence</code>.
     */
    public int getSize() {
        return mSize;
    }

    /**
     * Returns a list of all the chord members contained in a <code>ChordSequence</code>.
     *
     * @return ArrayList of all the chord members contained in a <code>ChordSequence</code>.
     */
    public ArrayList<Note> getAllChordMembers() {
        return mChordMembers;
    }

    /**
     * Clears all chord members contained in a <code>ChordScale</code> and resets its size to 0.
     */
    public void clearAllChordMembers() {
        mChordMembers.clear();
    }

    /**
     * Adds a new chord member to the end of a <code>ChordScale</code>.
     *
     * @param note <code>Note</code> object to be added to the end of a <code>ChordScale</code>.
     */
    public void addChordMember(Note note) {
        note.setDurationMilliseconds(this.mDurationMilliseconds);
        mChordMembers.add(note);
        mSize++;
    }

    /**
     * Inserts a new chord member at the specified position in <code>ChordScale</code>. The chord
     * member currently at that position (if any) and any subsequent chord members are shifted to the
     * right. <code>ChordScale</code> size is increased by 1.
     *
     * @param note <code>Note</code> object to be inserted into <code>ChordScale</code>.
     * @param pos position where <code>Note</code> object is to be inserted.
     */
    public void insertChordMemberAtPos(int pos, Note note) {
        note.setDurationMilliseconds(this.mDurationMilliseconds);
        mChordMembers.add(pos, note);
        mSize++;
    }

    /**
     * Gets a single chord member found at the specified position.
     *
     * @param pos specified position of the chord member.
     * @return a <code>Note</code> object found at specified position.
     */
    public Note getChordMemberAtPos(int pos) {
        return mChordMembers.get(pos);
    }

    /**
     * Gets a list of all the pitch frequencies of each chord member contained in the <code>ChordScale</code>.
     *
     * @return array of double values representing the frequencies in Hertz of all chord members.
     */
    public double[] getAllChordMemberFrequencies() {
        double[] frequencies = new double[mChordMembers.size()];
        for (int i = 0; i < mChordMembers.size(); ++i) {
            frequencies[i] = mChordMembers.get(i).getPitchFrequency();
        }
        return frequencies;
    }

    /**
     * Gets a list of ratios representing each chord member's relation to the root pitch in a
     * <code>ChordScale</code>.
     *
     * @return array of Strings representing each chord member's relation to the root pitch.
     */
    public String[] getAllChordMemberRatios() {
        String[] allChordMemberRatios = new String[this.mSize];
        int i = 0;
        for (Note note : this.mChordMembers) {
            allChordMemberRatios[i]
                    = convertDecimalToRatio(
                    this.mChordMembers.get(0).getPitchFrequency()
                            / this.mChordMembers.get(i).getPitchFrequency());
            i++;
        }
        return allChordMemberRatios;
    }

    /**
     * Gets the relationship between two chord members expressed as a simple whole-number ratio
     *
     * @param pos1 position of first chord member
     * @param pos2 position of second chord member
     * @return String containing a simple whole-number ratio representing the relationship between
     *          two chord members.
     */
    public String getIntervalRatio(int pos1, int pos2) {
        return convertDecimalToRatio(mChordMembers.get(pos1).getPitchFrequency()
                / mChordMembers.get(pos2).getPitchFrequency());
    }

    /**
     * Gets the distance measured in cents between any two chord members.
     *
     * Formula used (where m/n is the ratio): 1200*log(m/n)/log(2)
     *
     * @param pos1 position of first chord member
     * @param pos2 position of second chord member
     * @return double value representing the distance in cents between two chord members
     */
    public double getIntervalDistanceInCents(int pos1, int pos2) {
        // Get whole-number ratio
        String ratio = convertDecimalToRatio(this.getChordMemberAtPos(pos2).getPitchFrequency() /
                this.getChordMemberAtPos(pos1).getPitchFrequency());
        // Convert to cents
        return 1200*Math.log(parseRatio(ratio))/Math.log(2);
    }

    /**
     * Gets the playback mode of a <code>ChordScale</code>.
     *
     * @return String representing the playback mode of a <code>ChordScale</code>.
     */
    public String getPlayBackMode() {
        return mPlayBackMode;
    }

    /**
     * Sets the playback mode of a <code>ChordScale</code>.
     *
     * @param playBackMode String indicating the playback mode of a <code>ChordScale</code>.
     */
    public void setPlayBackMode(String playBackMode) {
        mPlayBackMode = playBackMode;
    }

    /**
     * Sets the duration of each chord member contained in the <code>ChordScale</code> to the same
     * specified duration in milliseconds.
     *
     * @param durationMilliseconds Duration in milliseconds to be assigned to each chord member.
     */
    @Override
    public void setDurationMilliseconds(int durationMilliseconds) {
        mDurationMilliseconds = durationMilliseconds;
        for (Note note : this.mChordMembers) {
            note.setDurationMilliseconds(durationMilliseconds);
        }
    }

    /**
     * Converts a decimal (double value) to a string representing the approximate fraction equivalent
     *
     * This method is used to express the relationship between two frequencies as a simple fraction
     *
     * @param decimal
     * @return A String representing an approximate whole-number ratio
     */
    private String convertDecimalToRatio(double decimal){
        if (decimal < 0){
            return "-" + convertDecimalToRatio(-decimal);
        }
        double tolerance = 1.0E-6;
        double m1 = 1; double m2 = 0;
        double n1 = 0; double n2 = 1;
        double b = decimal;
        do {
            double a = Math.floor(b);
            double aux = m1; m1 = a * m1 + m2; m2 = aux;
            aux = n1; n1 = a * n1 + n2; n2 = aux;
            b = 1 / (b - a);
        } while (Math.abs(decimal - m1 / n1) > decimal * tolerance);

        return (int) m1 + "/" + (int) n1;
    }

    /**
     * Converts a String representing a whole-number ratio into a decimal (double value).
     *
     * This ratio value is used to calculate a specified interval above a given fundamental frequency
     *
     * @param ratio A String representing a whole-number ratio
     * @return Converted decimal (double value)
     */
    private double parseRatio(String ratio) {
        if (ratio.contains("/")) {
            String[] rat = ratio.split("/");
            return Double.parseDouble(rat[0]) / Double.parseDouble(rat[1]);
        }
        else
            return Double.parseDouble(ratio);
    }
}