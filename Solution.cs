
using System;

public class Solution
{
    private static readonly int[] RANGE_OF_SKILLS = { 1, 1000 };
    private static readonly int NOT_VALID = int.MaxValue;
    private static readonly int NOT_POSSIBLE_TO_DIVIDE_PLAYERS_INTO_TEAMS_OF_TWO_WITH_EQUAL_SUM_OF_SKILLS = -1;

    public long DividePlayers(int[] skills)
    {
        int numberOfPlayers = skills.Length;
        int[] frequencySkills = CreateFrequencySkills(skills);
        return CalculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(frequencySkills, numberOfPlayers);
    }

    private int[] CreateFrequencySkills(int[] skills)
    {
        int[] frequencySkills = new int[RANGE_OF_SKILLS[1] + 1];
        foreach (int value in skills)
        {
            ++frequencySkills[value];
        }
        return frequencySkills;
    }

    private long CalculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(int[] frequencySkills, int numberOfPlayers)
    {
        int left = 0;
        int right = frequencySkills.Length - 1;

        int countNumberOfTeams = 0;
        int targetNumberOfTeams = numberOfPlayers / 2;
        long sumOfProductOfSkills = 0;
        int previousSumOfSkillsPerTeam = NOT_VALID;

        while (countNumberOfTeams < targetNumberOfTeams)
        {
            while (left < right && frequencySkills[left] == 0)
            {
                ++left;
            }
            while (left < right && frequencySkills[right] == 0)
            {
                --right;
            }

            int currentSumOfSkillsPerTeam = left + right;
            if (currentSumOfSkillsPerTeam != previousSumOfSkillsPerTeam && previousSumOfSkillsPerTeam != NOT_VALID)
            {
                return NOT_POSSIBLE_TO_DIVIDE_PLAYERS_INTO_TEAMS_OF_TWO_WITH_EQUAL_SUM_OF_SKILLS;
            }

            ++countNumberOfTeams;
            sumOfProductOfSkills += left * right;
            previousSumOfSkillsPerTeam = currentSumOfSkillsPerTeam;

            if (--frequencySkills[left] == 0)
            {
                ++left;
            }
            if (--frequencySkills[right] == 0)
            {
                --right;
            }
        }
        return sumOfProductOfSkills;
    }
}
