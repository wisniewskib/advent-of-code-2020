package day17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aoc.AoC;

public class ConwayCubes extends AoC {

	private static Set<Cube> activeCubes;
	private static Set<CubeTwo> activeCubesTwo;

	public static void main(String[] args) {
//		solve();
		solvePartTwo();
	}

	public static int solve() {
		List<String> lines = getInputAsList(17);

		activeCubes = getInitialActiveCubes(lines);

		Set<Cube> activeCubesCopy;
		int length = lines.size();

		for (int i = 1; i <= 6; i++) {
			activeCubesCopy = new HashSet<>(activeCubes);

			int minX = 0 - i, minY = 0 - i, minZ = 0 - i;
			int maxX = length + i, maxY = length + i, maxZ = 0 + i;

			for (int z = minZ; z <= maxZ; z++) {
				for (int y = minY; y <= maxY; y++) {
					for (int x = minX; x <= maxX; x++) {
						Cube cube = new Cube(z, y, x);
						int neighbours = countNeighbours(cube);
						if (activeCubes.contains(cube) && (neighbours > 3 || neighbours < 2)) {
							activeCubesCopy.remove(cube);
						} else if (!activeCubes.contains(cube) && neighbours == 3) {
							activeCubesCopy.add(cube);
						}
					}
				}
			}

			activeCubes = activeCubesCopy;
		}

		System.out.println(activeCubes.size());
		return activeCubes.size();
	}

	public static int solvePartTwo() {
		List<String> lines = getInputAsList(17);

		activeCubesTwo = getInitialActiveCubesTwo(lines);

		Set<CubeTwo> activeCubesTwoCopy;
		int length = lines.size();

		for (int i = 1; i <= 6; i++) {
			activeCubesTwoCopy = new HashSet<>(activeCubesTwo);

			int minX = 0 - i, minY = 0 - i, minZ = 0 - i, minW = 0 - i;
			int maxX = length + i, maxY = length + i, maxZ = 0 + i, maxW = 0 + i;

			for (int w = minW; w <= maxW; w++) {
				for (int z = minZ; z <= maxZ; z++) {
					for (int y = minY; y <= maxY; y++) {
						for (int x = minX; x <= maxX; x++) {
							CubeTwo cube = new CubeTwo(w, z, y, x);
							int neighbours = countNeighboursTwo(cube);
							if (activeCubesTwo.contains(cube) && (neighbours > 3 || neighbours < 2)) {
								activeCubesTwoCopy.remove(cube);
							} else if (!activeCubesTwo.contains(cube) && neighbours == 3) {
								activeCubesTwoCopy.add(cube);
							}
						}
					}
				}
			}

			activeCubesTwo = activeCubesTwoCopy;
		}

		System.out.println(activeCubesTwo.size());
		return activeCubesTwo.size();
	}

	private static Set<Cube> getInitialActiveCubes(List<String> lines) {
		Set<Cube> activeCubes = new HashSet<>();

		for (int y = 0; y < lines.size(); y++) {
			String line = lines.get(y);
			for (int x = 0; x < line.length(); x++) {
				char c = line.charAt(x);
				if (c == '#')
					activeCubes.add(new Cube(0, y, x));
			}
		}

		return activeCubes;
	}

	private static Set<CubeTwo> getInitialActiveCubesTwo(List<String> lines) {
		Set<CubeTwo> activeCubesTwo = new HashSet<>();

		for (int y = 0; y < lines.size(); y++) {
			String line = lines.get(y);
			for (int x = 0; x < line.length(); x++) {
				char c = line.charAt(x);
				if (c == '#')
					activeCubesTwo.add(new CubeTwo(0, 0, y, x));
			}
		}

		return activeCubesTwo;
	}
	
	private static int countNeighbours(Cube cube) {
		int count = 0;
		for (int z = cube.z - 1; z <= cube.z + 1; z++) {
			for (int y = cube.y - 1; y <= cube.y + 1; y++) {
				for (int x = cube.x - 1; x <= cube.x + 1; x++) {
					Cube cubeToCheck = new Cube(z, y, x);
					if (!cubeToCheck.equals(cube) && activeCubes.contains(cubeToCheck)) {
						count++;
					}
				}
			}
		}
		return count;
	}

	private static int countNeighboursTwo(CubeTwo cube) {
		int count = 0;
		for (int w = cube.w - 1; w <= cube.w + 1; w++) {
			for (int z = cube.z - 1; z <= cube.z + 1; z++) {
				for (int y = cube.y - 1; y <= cube.y + 1; y++) {
					for (int x = cube.x - 1; x <= cube.x + 1; x++) {
						CubeTwo cubeToCheck = new CubeTwo(w, z, y, x);
						if (!cubeToCheck.equals(cube) && activeCubesTwo.contains(cubeToCheck)) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}

	static class Cube {
		int z, y, x;

		public Cube(int z, int y, int x) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			result = prime * result + z;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			Cube c = (Cube) obj;
			return (c.x == x && c.y == y && c.z == z);
		}

		@Override
		public String toString() {
			return "Cube [z=" + z + ", y=" + y + ", x=" + x + "]" + "\n";
		}

	}

	static class CubeTwo extends Cube {
		int w;

		public CubeTwo(int w, int z, int y, int x) {
			super(z, y, x);
			this.w = w;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			result = prime * result + z;
			result = prime * result + w;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			CubeTwo c = (CubeTwo) obj;
			return (c.x == x && c.y == y && c.z == z && c.w == w);
		}

		@Override
		public String toString() {
			return "CubeTwo [w=" + w + ", z=" + z + ", y=" + y + ", x=" + x + "]" + "\n";
		}
	}

}

//System.out.println("maxX"+maxX);
//System.out.println("maxY"+maxY);
//System.out.println("maxZ"+maxZ);
//System.out.println("***");
//System.out.println("minZ"+minZ);
//System.out.println("minX"+minX);
//System.out.println("minYJ"+minY);
