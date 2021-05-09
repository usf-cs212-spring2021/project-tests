import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
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
		@Tag("verify") // FIXME Added tag
		public void testRFCs() {
			String link = "https://www.cs.usfca.edu/~cs212/rfcs/";
			testCounts(link, 7);
		}
		
		/**
		 * Tests the word counts for the given link and limit.
		 */
		@Test
		@Order(7)
		@Tag("verify") // FIXME Added tag
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
		// FIXME Removing tag
		// @Tag("verify")
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
		// FIXME Removing tag
		// @Tag("verify")
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
		// FIXME Removing tag
		// @Tag("verify")
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
		@RepeatedTest(2) // FIXME Reduced value from 2 to 3
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
			// FIXME Removed to reduce connections
			// testNoExceptions(args2, Duration.ofMinutes(3));

			// then test the timing
			assertTimeoutPreemptively(LONG_TIMEOUT, () -> {
				double result = compare("2 Workers", args1, String.valueOf(BENCH_THREADS) + " Workers", args2, 2, 4);

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
	
	// FIXME: Duplicating these temporarily to deal with network connectivity issues.
	
	/**
	 * Compares the runtime using two different sets of arguments. Outputs the
	 * runtimes to the console just in case there are any anomalies.
	 *
	 * @param label1 the label of the first argument set
	 * @param args1 the first argument set
	 * @param label2 the label of the second argument set
	 * @param args2 the second argument set
	 * @param warmup number of warmup rounds
	 * @param timed number of timed rounds 
	 * @return the runtime difference between the first and second set of arguments
	 */
	public static double compare(String label1, String[] args1, String label2, String[] args2, int warmup, int timed) {
		long[] runs1 = benchmark(args1, warmup, timed);
		long[] runs2 = benchmark(args2, warmup, timed);

		long total1 = 0;
		long total2 = 0;

		String labelFormat = "%-6s    %10s    %10s%n";
		String valueFormat = "%-6d    %10.6f    %10.6f%n";

		System.out.printf("%n```%n");
		System.out.printf(labelFormat, "Warmup", label1, label2);
		for (int i = 0; i < warmup; i++) {
			System.out.printf(valueFormat, i + 1,
					(double) runs1[i] / Duration.ofSeconds(1).toMillis(),
					(double) runs2[i] / Duration.ofSeconds(1).toMillis());
		}

		System.out.println();
		System.out.printf(labelFormat, "Timed", label1, label2);
		for (int i = warmup; i < warmup + timed; i++) {
			total1 += runs1[i];
			total2 += runs2[i];
			System.out.printf(valueFormat, i + 1,
					(double) runs1[i] / Duration.ofSeconds(1).toMillis(),
					(double) runs2[i] / Duration.ofSeconds(1).toMillis());
		}

		double average1 = (double) total1 / timed;
		double average2 = (double) total2 / timed;

		System.out.println();
		System.out.printf("%10s:  %10.6f seconds%n", label1, average1 / Duration.ofSeconds(1).toMillis());
		System.out.printf("%10s:  %10.6f seconds%n%n", label2, average2 / Duration.ofSeconds(1).toMillis());
		System.out.printf("%10s: x%10.6f %n", "Speedup", average1 / average2);
		System.out.printf("```%n%n");

		return average1 / average2;
	}

	/**
	 * Benchmarks the {@link Driver#main(String[])} method with the provided
	 * arguments. Tracks the timing of every run to allow of visual inspection.
	 *
	 * @param args the arguments to run
	 * @param warmup number of warmup rounds
	 * @param timed number of timed rounds
	 * @return an array of all the runtimes, including warmup runs and timed runs
	 */
	public static long[] benchmark(String[] args, int warmup, int timed) {
		long[] runs = new long[warmup + timed];

		Instant start;
		Duration elapsed;

		// suppress all console output for the warmup and timed runs
		PrintStream systemOut = System.out;
		PrintStream systemErr = System.err;

		PrintStream nullStream = new PrintStream(OutputStream.nullOutputStream());
		System.setOut(nullStream);
		System.setErr(nullStream);

		try {
			for (int i = 0; i < warmup; i++) {
				start = Instant.now();
				Driver.main(args);
				elapsed = Duration.between(start, Instant.now());
				runs[i] = elapsed.toMillis();
			}

			for (int i = 0; i < timed; i++) {
				start = Instant.now();
				Driver.main(args);
				elapsed = Duration.between(start, Instant.now());
				runs[i + warmup] = elapsed.toMillis();
			}
		}
		catch (Exception e) {
			StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));

			String debug = String.format("%nArguments:%n    [%s]%nException:%n    %s%n",
					String.join(" ", args), writer.toString());
			fail(debug);
		}
		finally {
			// restore console output
			System.setOut(systemOut);
			System.setErr(systemErr);
		}

		return runs;
	}
}
