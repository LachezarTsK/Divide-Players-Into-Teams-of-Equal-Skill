
public class Solution {

    private static final int[] RANGE_OF_SKILLS = {1, 1000};
    private static final int NOT_VALID = Integer.MAX_VALUE;
    private static final int NOT_POSSIBLE_TO_DIVIDE_PLAYERS_INTO_TEAMS_OF_TWO_WITH_EQUAL_SUM_OF_SKILLS = -1;

    public long dividePlayers(int[] skills) {
        int numberOfPlayers = skills.length;
        int[] frequencySkills = createFrequencySkills(skills);
        return calculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(frequencySkills, numberOfPlayers);
    }

    private int[] createFrequencySkills(int[] skills) {
        int[] frequencySkills = new int[RANGE_OF_SKILLS[1] + 1];
        for (int value : skills) {
            ++frequencySkills[value];
        }
        return frequencySkills;
    }

    private long calculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(int[] frequencySkills, int numberOfPlayers) {
        int left = 0;
        int right = frequencySkills.length - 1;

        int countNumberOfTeams = 0;
        int targetNumberOfTeams = numberOfPlayers / 2;
        long sumOfProductOfSkills = 0;
        int previousSumOfSkillsPerTeam = NOT_VALID;

        while (countNumberOfTeams < targetNumberOfTeams) {
            while (left < right && frequencySkills[left] == 0) {
                ++left;
            }
            while (left < right && frequencySkills[right] == 0) {
                --right;
            }

            int currentSumOfSkillsPerTeam = left + right;
            if (currentSumOfSkillsPerTeam != previousSumOfSkillsPerTeam && previousSumOfSkillsPerTeam != NOT_VALID) {
                return NOT_POSSIBLE_TO_DIVIDE_PLAYERS_INTO_TEAMS_OF_TWO_WITH_EQUAL_SUM_OF_SKILLS;
            }

            ++countNumberOfTeams;
            sumOfProductOfSkills += left * right;
            previousSumOfSkillsPerTeam = currentSumOfSkillsPerTeam;

            if (--frequencySkills[left] == 0) {
                ++left;
            }
            if (--frequencySkills[right] == 0) {
                --right;
            }
        }
        return sumOfProductOfSkills;
    }
}
