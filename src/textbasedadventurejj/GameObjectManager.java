package textbasedadventurejj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameObjectManager {

	private static volatile GameObjectManager INSTANCE;

	private Map<String, GameObject> objects;

	public void loadObjects() {
		objects = new HashMap<>();
		try {
			for (String s : Utils.readFile(Constants.ROOT + Constants.OBJECT_DIR + Constants.OBJECT_FILE).split("\n")) {
				String[] fullObj = s.split(":");
				String name = fullObj[0];
				GameObject object = Loader.loadObject(name);
				if (fullObj.length == 2) {
					object.setParent(objects.get(fullObj[1]));
				}
				objects.put(name, object);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public boolean verifyGameObjectName(String name) {
		return objects.containsKey(name);
	}

	public GameObject newObject(String name, String type) {
		if (!objects.containsKey(type))
			return null;
		return new GameObject(name, objects.get(type));
	}

	public GameObject newObject(String type) {
		return newObject(type, type);
	}

	private static class Loader {

		public static GameObject loadObject(String name) throws IOException {
			File file = new File(Constants.ROOT + Constants.OBJECT_DIR + name + File.separator + Constants.EVENT_FILE);
			GameObject object = new GameObject(name);
			object.addEvents(parseEvents(file));
			return object;
		}

		private static Map<String, Event> parseEvents(File eventFile) throws IOException {
			BufferedReader reader = new BufferedReader(new FileReader(eventFile));
			Map<String, Event> events = new HashMap<>();
			Map<String, Event> cache = new HashMap<>();
			String s = null;
			while ((s = reader.readLine()) != null) {
				String[] keyVal = s.split(":");
				if (keyVal.length != 2)
					continue;
				String name = keyVal[0];
				String fname = keyVal[1];
				Event event = null;
				if (cache.containsKey(fname)) {
					event = cache.get(fname);
				} else {
					event = readEvent(fname.split("\\.")[0],
							new File(eventFile.getParentFile().getAbsolutePath() + File.separatorChar + fname));
					cache.put(fname, event);
				}
				events.put(name, event);
			}
			reader.close();
			return events;
		}

		private static Event readEvent(String name, File event) throws IOException {
			return new Event(name, Utils.readFile(event).split("\n"));
		}
	}

	public static GameObjectManager getInstance() {
		if (INSTANCE == null) {
			synchronized (GameObjectManager.class) {
				if (INSTANCE == null) {
					INSTANCE = new GameObjectManager();
				}
			}
		}
		return INSTANCE;
	}
}