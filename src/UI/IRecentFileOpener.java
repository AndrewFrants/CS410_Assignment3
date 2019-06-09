/**
 * 
 */
package UI;


import java.nio.file.Path;
import java.util.List;

/**
 * @author andreyf
 * This class is the shared interface for command
 * Using the Strategy Pattern
 */
public interface IRecentFileOpener {

	void updateRecentPaths(List<Path> recentFiles);
}
