<p align="center">
  <a href="."><img alt="Sharlene Logo" src="https://gist.githubusercontent.com/d3cryptofc/83ab5ce9be9f311dbca82e2f9ba88f54/raw/534dfa189a62a9620263dad01cb85c64a447157e/sharlene-plain.svg" width="200"></a>
</p>

[![License](https://img.shields.io/badge/license-Apache_2.0-green?style=flat-square)](LICENSE)

# Sharlene

**Sharlene** is a command-line tool (CLI) designed to patch the bytecode of **Charles Proxy**. It allows you to locally modify Charles Proxy's behavior on your machine without distributing original or modified copies of the software.

```
Sharlene is a powerful patcher for Charles Proxy.

Usage: sharlene [-h | -V] ([-i=<jarfile>] -o=<jarfile> [-w])

General Options:
  -h, --help               Display this help information and exit
  -V, --version            Display program version and exit

File Options:
  -i, --input=<jarfile>    Input for original charles JAR file path
  -o, --output=<jarfile>   Output for patched charles JAR file path
  -w, --overwrite          Overwrite the output file
```

## Disclaimer

1. This repository will never distribute original or modified copies of Charles Proxy.
2. This tool modifies the program at the bytecode level locally on your machine.
3. I don't have authorization, and Charles Proxy maintainers don't encourage the use of this tool.
4. You modify Charles Proxy at your own will and absolve me of any responsibility, including:
   - Legal issues caused by modifying and/or distributing the software.
   - Bugs or security flaws resulting from using this tool.

## Clarifications and Motivations

**Charles Proxy** is an amazing tool, without a doubt the best among all proxy debugging tools I've tested. However, since it's closed-source software, we depend entirely on the maintainers to implement changes. This process can be slow or even result in rejection.

It's important to note that **I didn't contact Charles Proxy maintainers**, as it's obvious they wouldn't authorize or encourage patching their software.

## Requirements

- Java 21 or higher

## Contributions

Contributions are welcome! Feel free to open issues or pull requests. However, keep in mind the disclaimers and the purpose of this tool.

## License

This project is licensed under the [Apache License 2.0](LICENSE). See the [LICENSE](LICENSE) file for more details.

---

**Note**: This project is provided "as is", with no guarantees of any kind. Use at your own risk.
