package com.etudenhoefner.goeuro.model;

/**
 * Represents City information that were extracted from GoEuro's REST API.
 * 
 * @author Eduard Tudenhoefner
 * 
 */
public class City {
	private Long id;

	private String _type;

	private String name;

	private String type;

	private GeoPosition geoPosition;

	public City(Long id, String _type, String name, String type, GeoPosition geoPosition) {
		super();
		this.id = id;
		this._type = _type;
		this.name = name;
		this.type = type;
		this.geoPosition = geoPosition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String get_Type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public GeoPosition getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_type == null) ? 0 : _type.hashCode());
		result = prime * result + ((geoPosition == null) ? 0 : geoPosition.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (_type == null) {
			if (other._type != null)
				return false;
		} else if (!_type.equals(other._type))
			return false;
		if (geoPosition == null) {
			if (other.geoPosition != null)
				return false;
		} else if (!geoPosition.equals(other.geoPosition))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", _type=" + _type + ", name=" + name + ", type=" + type + ", geoPosition="
				+ geoPosition + "]";
	}

}
