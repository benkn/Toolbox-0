package ben.kn.toolbox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This service will convert an ivy file of dependencies to the maven format.
 * 
 * @author ben
 */
public class IvyToMavenService {
	private static final String MAVEN_FORMAT = "\t\t<dependency>\n"
			+ "\t\t\t<groupId>%s</groupId>\n" + "\t\t\t<artifactId>%s</artifactId>\n"
			+ "\t\t\t<version>%s</version>\n" + "\t\t</dependency>\n";

	private String ivyFile;

	public IvyToMavenService(String ivyFile) {
		this.ivyFile = ivyFile;
	}

	public static void main(String[] args) throws Exception {
		if ( args == null || args.length <= 0 ) {
			throw new RuntimeException("Usage: IvyToMavenService {path to ivy file}");
		}
		IvyToMavenService svc = new IvyToMavenService(args[0]);
		List<Dependency> dependencies = svc.getDependencies();
		String maven = svc.createMavenDependencies(dependencies);
		System.out.println(maven);
	}

	public List<Dependency> getDependencies() throws IOException {
		List<Dependency> dependencies = new ArrayList<Dependency>();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(ivyFile));

			String input;
			while ( (input = reader.readLine()) != null ) {
				if ( input.contains("dependency") ) {
					dependencies.add(constructDependency(input));
				}
			}
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
			}
		}

		return dependencies;
	}

	private Dependency constructDependency(String input) {
		Dependency d = new Dependency();

		String[] parts = input.split("\\s");
		for ( String string : parts ) {
			if ( string.startsWith("org") ) {
				d.org = string.substring("org=\"".length(), string.length() - 1);
			}
			if ( string.startsWith("name") ) {
				d.name = string.substring("name=\"".length(), string.length() - 1);
			}
			if ( string.startsWith("rev") ) {
				d.rev = string.substring("rev=\"".length(), string.length() - 1);
			}
		}

		return d;
	}

	public String createMavenDependencies(Collection<Dependency> dependencies) {
		StringBuilder sb = new StringBuilder();
		for ( Dependency d : dependencies ) {
			sb.append(String.format(MAVEN_FORMAT, d.org, d.name, d.rev));
		}
		return sb.toString();
	}

	public class Dependency {
		public String org;
		public String name;
		public String rev;

		@Override
		public String toString() {
			return "Dependency [org=" + org + ", name=" + name + ", rev=" + rev + "]";
		}

	}
}
