
package main

import (
    "fmt"
    "math"
)

var RANGE_OF_SKILLS = []int{1, 1000}

const NOT_VALID = math.MaxInt
const NOT_POSSIBLE_TO_DIVIDE_PLAYERS_INTO_TEAMS_OF_TWO_WITH_EQUAL_SUM_OF_SKILLS = -1

func dividePlayers(skills []int) int64 {
    numberOfPlayers := len(skills)
    frequencySkills := createFrequencySkills(skills)
    return calculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(frequencySkills, numberOfPlayers)
}

func createFrequencySkills(skills []int) []int {
    frequencySkills := make([]int, RANGE_OF_SKILLS[1] + 1)
    for _, value := range skills {
        frequencySkills[value]++
    }
    return frequencySkills
}

func calculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(frequencySkills []int, numberOfPlayers int) int64 {
    left := 0
    right := len(frequencySkills) - 1

    countNumberOfTeams := 0
    targetNumberOfTeams := numberOfPlayers / 2
    var sumOfProductOfSkills int64 = 0
    previousSumOfSkillsPerTeam := NOT_VALID

    for countNumberOfTeams < targetNumberOfTeams {
        for left < right && frequencySkills[left] == 0 {
            left++
        }
        for left < right && frequencySkills[right] == 0 {
            right--
        }

        currentSumOfSkillsPerTeam := left + right
        if currentSumOfSkillsPerTeam != previousSumOfSkillsPerTeam && previousSumOfSkillsPerTeam != NOT_VALID {
            return NOT_POSSIBLE_TO_DIVIDE_PLAYERS_INTO_TEAMS_OF_TWO_WITH_EQUAL_SUM_OF_SKILLS
        }

        countNumberOfTeams++
        sumOfProductOfSkills += int64(left * right)
        previousSumOfSkillsPerTeam = currentSumOfSkillsPerTeam

        frequencySkills[left]--
        if frequencySkills[left] == 0 {
            left++
        }

        frequencySkills[right]--
        if frequencySkills[right] == 0 {
            right--
        }
    }
    return sumOfProductOfSkills
}
