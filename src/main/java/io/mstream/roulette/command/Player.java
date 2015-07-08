package io.mstream.roulette.command;

import java.math.BigDecimal;

public class Player {

    private final String name;
    private final BigDecimal totalWin;
    private final BigDecimal totalBet;

    private Player(
            String name,
            BigDecimal totalWin,
            BigDecimal totalBet) {
        this.name = name;
        this.totalWin = totalWin;
        this.totalBet = totalBet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player that = (Player) o;

        if (!name.equals(that.name)) return false;
        if (!totalWin.equals(that.totalWin)) return false;
        return totalBet.equals(that.totalBet);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + totalWin.hashCode();
        result = 31 * result + totalBet.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTotalWin() {
        return totalWin;
    }

    public BigDecimal getTotalBet() {
        return totalBet;
    }

    public static class Builder {
        private final String name;
        private BigDecimal totalWin;
        private BigDecimal totalBet;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withTotalWin(BigDecimal totalWin) {
            this.totalWin = totalWin;
            return this;
        }

        public Builder withTotalBet(BigDecimal totalBet) {
            this.totalBet = totalBet;
            return this;
        }

        public Player build() {
            return new Player(
                    name,
                    totalWin != null ? totalWin : BigDecimal.ZERO,
                    totalBet != null ? totalBet : BigDecimal.ZERO);
        }
    }


}
