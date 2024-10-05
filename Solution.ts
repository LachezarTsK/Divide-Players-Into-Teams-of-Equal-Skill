
function dividePlayers(skills: number[]): number {
    this.RANGE_OF_SKILLS = [1, 1000];
    this.NOT_VALID = Number.MAX_VALUE;
    this.NOT_POSSIBLE_TO_DIVIDE_PLAYERS_INTO_TEAMS_OF_TWO_WITH_EQUAL_SUM_OF_SKILLS = -1;

    const numberOfPlayers = skills.length;
    const frequencySkills = createFrequencySkills(skills);
    return calculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(frequencySkills, numberOfPlayers);
};

function createFrequencySkills(skills: number[]): number[] {
    const frequencySkills: number[] = new Array(this.RANGE_OF_SKILLS[1] + 1).fill(0);
    for (let value of skills) {
        ++frequencySkills[value];
    }
    return frequencySkills;
}

function calculateSumOfProductsOfSkillsByDividingPlayersIntoTeamsOfTwo(frequencySkills: number[], numberOfPlayers: number): number {
    let left = 0;
    let right = frequencySkills.length - 1;

    let countNumberOfTeams = 0;
    let targetNumberOfTeams = Math.floor(numberOfPlayers / 2);
    let sumOfProductOfSkills = 0;
    let previousSumOfSkillsPerTeam = this.NOT_VALID;

    while (countNumberOfTeams < targetNumberOfTeams) {
        while (left < right && frequencySkills[left] === 0) {
            ++left;
        }
        while (left < right && frequencySkills[right] === 0) {
            --right;
        }

        let currentSumOfSkillsPerTeam = left + right;
        if (currentSumOfSkillsPerTeam !== previousSumOfSkillsPerTeam && previousSumOfSkillsPerTeam !== this.NOT_VALID) {
            return this.NOT_POSSIBLE_TO_DIVIDE_PLAYERS_INTO_TEAMS_OF_TWO_WITH_EQUAL_SUM_OF_SKILLS;
        }

        ++countNumberOfTeams;
        sumOfProductOfSkills += left * right;
        previousSumOfSkillsPerTeam = currentSumOfSkillsPerTeam;

        if (--frequencySkills[left] === 0) {
            ++left;
        }
        if (--frequencySkills[right] === 0) {
            --right;
        }
    }
    return sumOfProductOfSkills;
}
