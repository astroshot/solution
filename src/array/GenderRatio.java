package array;

import java.util.Random;

/**
 * Created by dell on 2017/4/25.
 */
public class GenderRatio {
    /**
     * 澳大利亚的父母喜欢女孩，如果生出来的第一个女孩，就不再生了，如果是男孩就继续生
     * 直到生到第一个女孩为止，问若干年后，男女的比例是多少
     */
    private static Random rand = new Random();
    private static int maleNum = 0;
    private static int femaleNum = 0;

    enum Gender {
        MALE, FEMALE;
    }

    private static Gender[] genders = {Gender.MALE, Gender.FEMALE};
    /**
     * Get a child, 0 represents boy, 1 represents girl
     * @return 0 or 1
     */
    private static Gender getChild() {
        return genders[Math.abs(rand.nextInt()) % 2];
    }

    private static void addChilds(Gender child) {
        if (child == Gender.MALE) { // boy
            maleNum++;
        } else {
            femaleNum++;
        }
    }

    private static void familyGetChilds() {
        Gender child = getChild();
        if (child == Gender.MALE) { // boy
            addChilds(child);
            familyGetChilds();
        } else { // girl
            addChilds(child);
        }
    }

    private static void printResults() {
        System.out.printf("Now there are %d males and %d females, and the ratio of male to female is %f\n",
                maleNum, femaleNum, maleNum * 1.0 / femaleNum);
    }

    public static void population(int familyNum) {
        for (int i = 0; i < familyNum; i++) {
            familyGetChilds();
        }
        printResults();
    }
}
