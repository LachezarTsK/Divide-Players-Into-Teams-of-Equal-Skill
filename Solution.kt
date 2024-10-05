
class Solution {

    private companion object {
        val RANGE_OF_SKILLS = intArrayOf(1, 1000)
        const val NOT_VALID = Int.MAX_VALUE
        const val NOT_POSSIBLE_TO_DIVIDE_PLAYERS_INTO_TEAMS_OF_TWO_WITH_EQUAL_SUM_OF_SKILLS: Long = -1
    }

    fun dividePlayers(skills: IntArray): Long {
        val numberOfPlayers = skills.size
        val frequencySkills = createFrequencySkills(skills)
        return calculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(frequencySkills, numberOfPlayers)
    }

    private fun createFrequencySkills(skills: IntArray): IntArray {
        val frequencySkills = IntArray(RANGE_OF_SKILLS[1] + 1)
        for (value in skills) {
            ++frequencySkills[value]
        }
        return frequencySkills
    }

    private fun calculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(frequencySkills: IntArray, numberOfPlayers: Int): Long {
        var left = 0
        var right = frequencySkills.size - 1

        var countNumberOfTeams = 0
        var targetNumberOfTeams = numberOfPlayers / 2
        var sumOfProductOfSkills: Long = 0
        var previousSumOfSkillsPerTeam = NOT_VALID

        while (countNumberOfTeams < targetNumberOfTeams) {
            while (left < right && frequencySkills[left] == 0) {
                ++left
            }
            while (left < right && frequencySkills[right] == 0) {
                --right
            }

            val currentSumOfSkillsPerTeam = left + right
            if (currentSumOfSkillsPerTeam != previousSumOfSkillsPerTeam && previousSumOfSkillsPerTeam != NOT_VALID) {
                return NOT_POSSIBLE_TO_DIVIDE_PLAYERS_INTO_TEAMS_OF_TWO_WITH_EQUAL_SUM_OF_SKILLS
            }

            ++countNumberOfTeams
            sumOfProductOfSkills += left * right
            previousSumOfSkillsPerTeam = currentSumOfSkillsPerTeam

            if (--frequencySkills[left] == 0) {
                ++left
            }
            if (--frequencySkills[right] == 0) {
                --right
            }
        }
        return sumOfProductOfSkills
    }
}
