
#include <span>
#include <array>
#include <limits>
#include <vector>
using namespace std;

/*
The code will run faster with ios::sync_with_stdio(0).
However, this should not be used in production code and interactive problems.
In this particular problem, it is ok to apply ios::sync_with_stdio(0).

Many of the top-ranked C++ solutions for time on leetcode apply this trick,
so, for a fairer assessment of the time percentile of my code
you could outcomment the lambda expression below for a faster run.
*/

/*
const static auto speedup = [] {
    ios::sync_with_stdio(0);
    return nullptr;
}();
*/

class Solution {

    static constexpr array<int, 2> RANGE_OF_SKILLS = { 1, 1000 };
    static const int NOT_VALID = numeric_limits<int>::max();
    static const int NOT_POSSIBLE_TO_DIVIDE_PLAYERS_INTO_TEAMS_OF_TWO_WITH_EQUAL_SUM_OF_SKILLS = -1;

public:
    long long dividePlayers(const vector<int>& skills) const {
        int numberOfPlayers = skills.size();
        array<int, RANGE_OF_SKILLS[1] + 1> frequencySkills = createFrequencySkills(skills);
        return calculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(frequencySkills, numberOfPlayers);
    }

private:
    array<int, RANGE_OF_SKILLS[1] + 1> createFrequencySkills(span<const int> skills) const {
        array<int, RANGE_OF_SKILLS[1] + 1> frequencySkills{};
        for (int value : skills) {
            ++frequencySkills[value];
        }
        return frequencySkills;
    }

    long long calculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(span<int> frequencySkills, int numberOfPlayers) const {
        int left = 0;
        int right = frequencySkills.size() - 1;

        int countNumberOfTeams = 0;
        int targetNumberOfTeams = numberOfPlayers / 2;
        long long sumOfProductOfSkills = 0;
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
};
