package it.univpm.progetto.model;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe per descrivere un generico file
 * @author Devid
 *
 */
public abstract class File {
	private String name;
	private Path path;
	private long size;

	public File() {
		this.name = null;
		this.path = null;
		this.size = 0;
	}

	/**
	 * @param name
	 * @param path
	 * @param size
	 */
	public File(String name, Path path, long size) {
		this.name = name;
		this.path = path;
		this.size = size;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = Paths.get(path);
	}
	
	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}
	
	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	public abstract boolean isMultimediale();


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		File other = (File) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "name= " + name + ", path= " + path;
	}

}
