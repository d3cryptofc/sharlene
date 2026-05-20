# Sharlene

[![License](https://img.shields.io/badge/license-Apache_2.0-blue?style=flat-square&color=orange)](LICENSE)

**Sharlene** is a command-line tool (CLI) designed to patch the bytecode of **Charles Proxy**. It allows you to locally modify Charles Proxy's behavior on your machine without distributing original or modified copies of the software.

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
