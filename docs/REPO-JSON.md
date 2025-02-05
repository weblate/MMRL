# `repo.json` documentation

The core of detailing modules in [Googlers-Repo/magisk-modules-repo-util](https://github.com/Googlers-Repo/magisk-modules-repo-util)

## Supported Repositories

Every repository that uses the [GR Repo Format](https://github.com/Googlers-Repo/magisk-modules-repo-util) should support this feature.

> [!TIP]
> Non-array- and non-objects can also placed inside the `module.prop` file!
> 
> To use this feature you need to create a file named `common/repo.json` in your module root folder

### support

The URL to your project's support page, forum, or issue tracker where users can get help or report problems.

Example:

```json
"support": "https://github.com/owner/project/issues"
```

### donate

The URL to the donation page for your project, where users can financially support its development.

Example:

```json
"donate": "https://example.com/donate"
```

### cover

The URL to a cover image representing the project. This should typically be a featured graphic for the project.

Example:

```json
"cover": "https://example.com/cover.png"
```

### icon

The URL to the icon image of the project, which should be squared and no larger than 512x512 pixels.

Example:

```json
"icon": "https://example.com/icon.png"
```

### license

An SPDX identifier specifying the license for the project.

<sub>_For SPDX identifiers, see the [SPDX license list](https://spdx.org/licenses/)._</sub>

Example:

```json
"license": "MIT"
```

### readme

The URL to the project's README file, which typically contains information like a project description and setup instructions.

Example:

```json
"readme": "https://github.com/owner/project#readme"
```

### homepage

The URL to the homepage of the project.

Example:

```json
"homepage": "https://example.com"
```

### screenshots

An array of URLs to screenshots of the module.

Example:

```json
"screenshots": [
  "https://example.com/screenshot1.png",
  "https://example.com/screenshot2.png"
]
```

### category

> [!CAUTION]
> This property is not supported in MMRL V4 and above

The category the module belongs to. This field is deprecated.

Example:

```jsonc
"category": "Utility"
```

### categories

> [!IMPORTANT]
> Repository owners can set a whitelist to prevent spam and abuse

An array of categories the module belongs to.

Example:

```json
"categories": ["Utility", "Tools"]
```

### require

An array of `module_id`s this module depends on.

Example:

```json
"require": [
  "com.example.module1",
  "com.example.module2"
]
```

### note

> [!CAUTION]
> The `color` property is not supported in MMRL V4 and above

An additional note for the module. This is an optional field, but if it's defined, the `message` field is required.

Example:

```json
"note": {
  "title": "Important Update",
  "color": "red",
  "message": "This module requires Magisk version 24.0 or higher."
}
```

### root

> [!NOTE]
> If you want to implant this into your app, you should follow the [semver](https://www.npmjs.com/package/semver) syntax

Defines the minimum version requirements for root solutions such as Magisk, KernelSU, or APatch.

Example:

```json
"root": {
  "kernelsu": ">= 1.0.0",
  "magisk": ">= 24.0"
}
```
