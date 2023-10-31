export interface INative<T = any> {
  get getInterface(): T;
  get userAgent(): string;
}

export type NativeArgumentTypes<F extends Function> = F extends (...args: infer A) => any ? A : never;

/**
 * Core functions for native functions/interfaces
 */
export class Native<I = any> implements INative<I> {
  /**
   * This field is required, otherwise the comunacation between Android will not work
   * @required true
   */
  public interfaceName: keyof AndroidWindow<I> | undefined;

  public constructor() {}

  private get userAgentRegex(): RegExp {
    return /MMRL\/(.+)\s\(Linux;\sAndroid\s(.+);\s(.+)\sBuild\/(.+)\)/gs;
  }

  public get userAgent(): string {
    return window.navigator.userAgent;
  }

  /**
   * Determine if MMRL runs on a Android device
   */
  public get isAndroid(): boolean {
    return this.userAgentRegex.test(this.userAgent) || window.hasOwnProperty("cordova") ? true : false;
  }

  /**
   * @deprecated Use `Native.interface()` instead
   */
  public get getInterface(): I {
    if (this.interfaceName) {
      return window[this.interfaceName];
    } else {
      throw new Error("No interface defined");
    }
  }

  public interface<K extends keyof I>(name: K): I[K] {
    if (this.interfaceName) {
      if (typeof window[this.interfaceName][name] == "undefined") {
        return (window[this.interfaceName][name] = () => {
          console.log("Undefined function");
        }) as any;
      } else {
        return window[this.interfaceName][name];
      }
    } else {
      throw new Error("No interface defined");
    }
  }
}
