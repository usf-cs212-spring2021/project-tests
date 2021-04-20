import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * A test suite for project 3. During development, run individual tests instead
 * of this entire test suite!
 * 
 * THIS IS THE RUNTIME SUITE OF TESTS. IT TAKES VERY LONG TO RUN. DO NOT RUN
 * UNLESS YOU ARE PASSING THE OUTPUT TESTS FIRST.
 * 
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Spring 2021
 */
@TestMethodOrder(MethodName.class)
public class Project4Test extends TestUtilities {
	/**
	 * Make sure previous projects seems to work.
	 */
	@Test
	@Tag("verify")
	public void testPrevious() {
		var project3 = new Project3aTest().new C_PartialSearchTest();
		project3.setup();
		project3.testTextDirectory(Project3bTest.BENCH_THREADS);
	}
	
	/**
	 * Tests the output of this project.
	 */
	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	public class A_IndexTest {
		/**
		 * Tests project output. 
		 * 
		 * @param seed the seed URL
		 */
		@Order(1)
		@ParameterizedTest
		@ValueSource(strings = {
				"https://www.cs.usfca.edu/~cs212/simple/hello.html",
				"https://www.cs.usfca.edu/~cs212/simple/mixed_case.htm",
				"https://www.cs.usfca.edu/~cs212/simple/position.html",
				"https://www.cs.usfca.edu/~cs212/simple/symbols.html",
				"https://www.cs.usfca.edu/~cs212/birds/yellowthroat.html"
		})
		public void testSimpleFiles(String seed) {
			testIndex(seed, "simple", 1);
		}
		
		/** Tests project output. */
		@Test
		@Order(2)
		public void testSimple() {
			String seed = "https://www.cs.usfca.edu/~cs212/simple/";
			testIndex(seed, "simple", 10);
		}
		
		/** Tests project output. */
		@Test
		@Order(3)
		public void testBirds() {
			String seed = "https://www.cs.usfca.edu/~cs212/birds/birds.html";
			testIndex(seed, "simple", 50);
		}

		/** Tests project output. */
		@Test
		@Order(4)
		public void testRecurse() {
			String seed = "https://www.cs.usfca.edu/~cs212/recurse/link01.html";
			testIndex(seed, "other", 100);
		}

		/** Tests project output. */
		@Test
		@Order(5)
		public void testRedirect() {
			String seed = "https://www.cs.usfca.edu/~cs212/redirect/index.html";
			testIndex(seed, "other", 10);
		}

		/** Tests project output. */
		@Test
		@Order(6)
		@Tag("verify")
		public void testLocal() {
			String seed = "https://www.cs.usfca.edu/~cs212/local.html";
			testIndex(seed, "other", 200);
		}
		
		/**
		 * Tests project output. 
		 * 
		 * @param seed the seed URL
		 */
		@Order(7)
		@ParameterizedTest
		@ValueSource(strings = {
				"https://www.cs.usfca.edu/~cs212/rfcs/rfc475.html",
				"https://www.cs.usfca.edu/~cs212/rfcs/rfc3629.html",
				"https://www.cs.usfca.edu/~cs212/rfcs/rfc5646.html",
				"https://www.cs.usfca.edu/~cs212/rfcs/rfc6805.html",
				"https://www.cs.usfca.edu/~cs212/rfcs/rfc6838.html",
				"https://www.cs.usfca.edu/~cs212/rfcs/rfc7231.html"
		})
		public void testRFCFiles(String seed) {
			testIndex(seed, "rfcs", 1);
		}
		
		/** Tests project output. */
		@Test
		@Order(8)
		public void testRFCs() {
			String seed = "https://www.cs.usfca.edu/~cs212/rfcs/";
			testIndex(seed, "rfcs", 7);
		}
		
		/**
		 * Tests project output. 
		 * 
		 * @param seed the seed URL
		 */
		@Order(9)
		@ParameterizedTest
		@ValueSource(strings = {
				"https://www.cs.usfca.edu/~cs212/guten/37134-h/37134-h.htm",
				"https://www.cs.usfca.edu/~cs212/guten/22577-h/22577-h.htm",
				"https://www.cs.usfca.edu/~cs212/guten/1661-h/1661-h.htm",
				"https://www.cs.usfca.edu/~cs212/guten/1322-h/1322-h.htm",
				"https://www.cs.usfca.edu/~cs212/guten/2701-h/2701-h.htm",
				"https://www.cs.usfca.edu/~cs212/guten/50468-h/50468-h.htm",
				"https://www.cs.usfca.edu/~cs212/guten/1400-h/1400-h.htm"
		})
		public void testGutenFiles(String seed) {
			testIndex(seed, "guten", 1);
		}
		
		/** Tests project output. */
		@Test
		@Order(10)
		public void testGuten() {
			String seed = "https://www.cs.usfca.edu/~cs212/guten/";
			testIndex(seed, "guten", 8);
		}
		
		/**
		 * Tests project output. 
		 * 
		 * @param seed the seed URL
		 */
		@Order(11)
		@ParameterizedTest
		@ValueSource(strings = {
				"https://www.cs.usfca.edu/~cs212/javadoc/api/java.base/java/util/AbstractCollection.html",
				"https://www.cs.usfca.edu/~cs212/javadoc/api/java.compiler/javax/lang/model/SourceVersion.html",
				"https://www.cs.usfca.edu/~cs212/javadoc/api/java.desktop/java/awt/desktop/AboutHandler.html",
				"https://www.cs.usfca.edu/~cs212/javadoc/api/java.prefs/java/util/prefs/AbstractPreferences.html",
				"https://www.cs.usfca.edu/~cs212/javadoc/api/overview-tree.html"
		})
		public void testJavaFiles(String seed) {
			testIndex(seed, "other", 1);
		}
		
		/** Tests project output. */
		@Test
		@Order(12)
		public void testJava() {
			String seed = "https://www.cs.usfca.edu/~cs212/javadoc/api/allclasses-index.html";
			testIndex(seed, "other", 50);
		}
	}
	
	/**
	 * Tests the output of this project.
	 */
	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	public class B_CountTest {
		/**
		 * Tests the word counts for the given link and limit.
		 */
		@Test
		@Order(1)
		public void testSimple() {
			String link = "https://www.cs.usfca.edu/~cs212/simple/";
			testCounts(link, 10);
		}
		
		/**
		 * Tests the word counts for the given link and limit.
		 */
		@Test
		@Order(2)
		public void testBirds() {
			String link = "https://www.cs.usfca.edu/~cs212/birds/birds.html";
			testCounts(link, 50);
		}
		
		/**
		 * Tests the word counts for the given link and limit.
		 */
		@Test
		@Order(3)
		public void testRecurse() {
			String link = "https://www.cs.usfca.edu/~cs212/recurse/link01.html";
			testCounts(link, 100);
		}

		/**
		 * Tests the word counts for the given link and limit.
		 */
		@Test
		@Order(4)
		public void testRedirect() {
			String link = "https://www.cs.usfca.edu/~cs212/redirect/index.html";
			testCounts(link, 10);
		}
		
		/**
		 * Tests the word counts for the given link and limit.
		 */
		@Test
		@Order(5)
		@Tag("verify")
		public void testLocal() {
			String link = "https://www.cs.usfca.edu/~cs212/local.html";
			testCounts(link, 200);
		}

		/**
		 * Tests the word counts for the given link and limit.
		 */
		@Test
		@Order(6)
		public void testRFCs() {
			String link = "https://www.cs.usfca.edu/~cs212/rfcs/";
			testCounts(link, 7);
		}
		
		/**
		 * Tests the word counts for the given link and limit.
		 */
		@Test
		@Order(7)
		public void testGutenberg() {
			String link = "https://www.cs.usfca.edu/~cs212/guten/";
			testCounts(link, 8);
		}

		/**
		 * Tests the word counts for the given link and limit.
		 */
		@Test
		@Order(8)
		public void testJava() {
			String link = "https://www.cs.usfca.edu/~cs212/javadoc/api/allclasses-index.html";
			testCounts(link, 50);
		}
	}

	/**
	 * Tests the output of this project.
	 */
	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	public class C_SearchTest {
		/** 
		 * Tests the search output. 
		 * @param exact whether to conduct exact or partial search
		 */
		@Order(1)
		@ParameterizedTest
		@ValueSource(booleans = {true, false})
		public void testSimple(boolean exact) {
			String seed = "https://www.cs.usfca.edu/~cs212/simple/";
			int max = 10;
			String query = "simple.txt";
			testSearch(seed, max, exact, query);
		}
		
		/** 
		 * Tests the search output. 
		 * @param exact whether to conduct exact or partial search
		 */
		@Order(2)
		@ParameterizedTest
		@ValueSource(booleans = {true, false})
		public void testBirds(boolean exact) {
			String seed = "https://www.cs.usfca.edu/~cs212/birds/birds.html";
			int max = 50;
			String query = "letters.txt";
			testSearch(seed, max, exact, query);
		}
		
		/** 
		 * Tests the search output. 
		 * @param exact whether to conduct exact or partial search
		 */
		@Order(3)
		@ParameterizedTest
		@ValueSource(booleans = {true, false})
		@Tag("verify")
		public void testLocal(boolean exact) {
			String seed = "https://www.cs.usfca.edu/~cs212/local.html";
			int max = 200;
			String query = "letters.txt";
			testSearch(seed, max, exact, query);
		}
		
		/** 
		 * Tests the search output. 
		 * @param exact whether to conduct exact or partial search
		 */
		@Order(4)
		@ParameterizedTest
		@ValueSource(booleans = {true, false})
		@Tag("verify")
		public void testRFCs(boolean exact) {
			String seed = "https://www.cs.usfca.edu/~cs212/rfcs/";
			int max = 7;
			String query = "letters.txt";
			testSearch(seed, max, exact, query);
		}
		
		/** 
		 * Tests the search output. 
		 * @param exact whether to conduct exact or partial search
		 */
		@Order(5)
		@ParameterizedTest
		@ValueSource(booleans = {true, false})
		@Tag("verify")
		public void testGuten(boolean exact) {
			String seed = "https://www.cs.usfca.edu/~cs212/guten/";
			int max = 8;
			String query = "complex.txt";
			testSearch(seed, max, exact, query);
		}
		
		/** 
		 * Tests the search output. 
		 * @param exact whether to conduct exact or partial search
		 */
		@Order(6)
		@ParameterizedTest
		@ValueSource(booleans = {true, false})
		@Tag("verify")
		public void testJava(boolean exact) {
			String seed = "https://www.cs.usfca.edu/~cs212/javadoc/api/allclasses-index.html";
			int max = 50;
			String query = "letters.txt";
			testSearch(seed, max, exact, query);
		}
	}
	
	/**
	 * Tests the runtime and consistency of this project.
	 */
	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	@Tag("verify")
	public class D_RuntimeTest {
		/**
		 * Tests that the inverted index output remains consistent when repeated.
		 */
		@Order(1)
		@RepeatedTest(3)
		public void testIndexConsistency() {
			new A_IndexTest().testJava();
		}
		
		/**
		 * Tests that code runs faster with {@value #BENCH_THREADS} threads is 1.1x
		 * faster versus just 1 worker thread.
		 */
		@Order(2)
		@Test
		public void testIndexMultithreaded() {
			String link = "https://www.cs.usfca.edu/~cs212/javadoc/api/allclasses-index.html";
			int limit = 30; // smaller to speed up benchmark

			String[] args1 = {
					HTML_FLAG, link,
					MAX_FLAG, Integer.toString(limit),
					THREADS_FLAG, String.valueOf(2) };

			String[] args2 = {
					HTML_FLAG, link,
					MAX_FLAG, Integer.toString(limit),
					THREADS_FLAG, String.valueOf(BENCH_THREADS) };

			System.out.println();
			System.out.printf("### Testing Build 2 vs %d Workers...%n", BENCH_THREADS);

			// make sure code runs without exceptions before testing
			testNoExceptions(args1, Duration.ofMinutes(3));
			testNoExceptions(args2, Duration.ofMinutes(3));

			// then test the timing
			assertTimeoutPreemptively(LONG_TIMEOUT, () -> {
				double result = Project3bTest.compare("2 Workers", args1, String.valueOf(BENCH_THREADS) + " Workers", args2);

				assertTrue(result >= 1.1, () -> String.format(
						"%d workers has a %.2fx speedup (less than the 1.1x required) compareed to %s.", 
						BENCH_THREADS, result, "2 workers"));
			});
		}
	}
	
	/** Where to locate expected files for web crawling. */
	public static final Path EXPECTED_CRAWL = EXPECTED_PATH.resolve("crawl");
	
	/** The default number of threads to use when benchmarking. */
	public static final int BENCH_THREADS = 5;
	
	/**
	 * Runs an individual test of the web crawler inverted index output.
	 *
	 * @param link the link to test
	 * @param subdir the subdir to use
	 * @param limit the limit to use
	 */
	public static void testIndex(String link, String subdir, int limit) {
		String name = getTestName(link);
		String filename = String.format("index-%s.json", name);

		Path actual = ACTUAL_PATH.resolve(filename);
		Path expected = EXPECTED_CRAWL.resolve("index-" + subdir).resolve(filename);

		String[] args = {
			HTML_FLAG, link,
			MAX_FLAG, Integer.toString(limit),
			THREADS_FLAG, Integer.toString(THREADS_DEFAULT),
			INDEX_FLAG, actual.normalize().toString()
		};

		Assertions.assertTimeoutPreemptively(LONG_TIMEOUT, () -> {
			TestUtilities.checkOutput(args, actual, expected);
		});
	}
	
	/**
	 * Runs an individual test of the word count.
	 *
	 * @param link the link to test
	 * @param limit the limit to use
	 */
	public static void testCounts(String link, int limit) {
		String name = getTestName(link);
		String filename = String.format("counts-%s.json", name);

		Path actual = ACTUAL_PATH.resolve(filename);
		Path expected = EXPECTED_CRAWL.resolve("counts").resolve(filename);

		String[] args = {
			HTML_FLAG, link,
			MAX_FLAG, Integer.toString(limit),
			THREADS_FLAG, Integer.toString(THREADS_DEFAULT),
			COUNTS_FLAG, actual.normalize().toString()
		};

		Assertions.assertTimeoutPreemptively(LONG_TIMEOUT, () -> {
			checkOutput(args, actual, expected);
		});
	}
	
		/**
		 * Runs an individual test of the web crawler search output.
		 *
		 * @param link the link to test
		 * @param limit the limit to use
		 * @param exact whether it is an exact search
		 * @param query the query file to use
		 */
		public static void testSearch(String link, int limit, boolean exact, String query) {
			String name = getTestName(link);
			String type = exact ? "exact" : "partial";
			String filename = String.format("search-%s-%s.json", type, name);

			Path actual = ACTUAL_PATH.resolve(filename);
			Path expected = EXPECTED_CRAWL.resolve("search-" + type).resolve(filename);

			String[] args = {
					HTML_FLAG, link,
					MAX_FLAG, Integer.toString(limit),
					QUERY_FLAG, QUERY_PATH.resolve(query).toString(),
					RESULTS_FLAG, actual.normalize().toString(),
					THREADS_FLAG, Integer.toString(THREADS_DEFAULT),
					exact ? EXACT_FLAG: ""
			};

			Assertions.assertTimeoutPreemptively(LONG_TIMEOUT, () -> {
				checkOutput(args, actual, expected);
			});
		}	
	
	/**
	 * Gets the name to use for test output files based on the link.
	 *
	 * @param link the link to test
	 * @return the name to use for test output
	 */
	public static String getTestName(String link) {
		Pattern regex = Pattern.compile(".*/~cs212/(?:([^/]+)/)?.*?(?:([^/]*)\\.html?)?");
		Matcher matcher = regex.matcher(link);
		
		if (matcher.matches()) {
			String path = matcher.group(1);
			String file = matcher.group(2);

			if (path == null || path.isBlank()) {
				return file;
			}
			
			// special cases
			if (file == null || file.isBlank() || file.endsWith("index") || file.endsWith("link01")) {
				return path;
			}

			return file;
		}

		return "";
	}
}
