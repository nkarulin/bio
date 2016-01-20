package com.KNS;

/**
 * Created by Nikolai_Karulin on 1/18/2016.
 *
 * Given a profile matrix Profile, we can evaluate the probability of every k-mer in a string Text and find a
 * Profile-most probable k-mer in Text, i.e., a k-mer that was most likely to have been generated by Profile
 * among all k-mers in Text. For example, ACGGGGATTACC is the Profile-most probable 12-mer in GGTACGGGGATTACCT.
 * Indeed, every other 12-mer in this string has probability 0.
 */
public class ProfileMostProbableKmerFinder {

    public static double checkProbability(String kmer, double[][] profile) {

        double probability = 1;
        for (int pos = 0; pos < kmer.length(); pos++) {
            char base = kmer.charAt(pos);

            switch (base) {
                case 'A':
                    probability *= profile[0][pos];
                    break;
                case 'C':
                    probability *= profile[1][pos];
                    break;
                case 'G':
                    probability *= profile[2][pos];
                    break;
                case 'T':
                    probability *= profile[3][pos];
                    break;
                default:
                    probability *= 0;
            }
        }

        return probability;
    }

    /**
     * Find a Profile-most probable k-mer in a string.
     * @param Text
     * @param k - kmer length
     * @param profile
     * @return A Profile-most probable k-mer in Text.
     */
    public static String find(String Text, int k, double[][] profile) {

        double recordProbability = 0;
        String recordKmer = "";
//        System.out.printf("Probablis: ");
        for (int i = 0; i <= Text.length() - k; i++) {
            String kmer = Text.substring(i, i + k);

            double probability = checkProbability(kmer, profile);
//            System.out.printf(",%.4f", probability);
            if (probability > recordProbability) {
                recordProbability = probability;
                recordKmer = kmer;
//                System.out.printf("!");
            }

        }

//        System.out.printf(" => %s(%f)\n", recordKmer, recordProbability);
        return recordKmer;
    }

    public static void main(String[] args) {

        double profile[][] = {
                {0.2, 0.2, 0.3, 0.2, 0.3},
                {0.4, 0.3, 0.1, 0.5, 0.1},
                {0.3, 0.3, 0.5, 0.2, 0.4},
                {0.1, 0.2, 0.1, 0.1, 0.2}
        };

        String kmer = ProfileMostProbableKmerFinder.find(
                "ACCTGTTTATTGCCTAAGTTCCGAACAAACCCAATATAGCCCGAGGGCCT",
                5,
                profile
        );

        System.out.printf("Result: %s\n", kmer);
    }

}
