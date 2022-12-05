
// 菜单列表项声明
export interface MenuItem {
    id: number,
      path: string,
      authName: string,
      children: Array < MenuItem >
  }