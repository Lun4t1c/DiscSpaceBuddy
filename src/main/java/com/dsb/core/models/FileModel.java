package com.dsb.core.models;

import java.nio.file.Path;

public record FileModel(Path path, long size) {
}
